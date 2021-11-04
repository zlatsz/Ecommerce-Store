package sistersart.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sistersart.error.OrderNotFoundException;
import sistersart.model.entity.Order;
import sistersart.model.service.OrderProductServiceModel;
import sistersart.model.service.OrderServiceModel;
import sistersart.model.service.ProductServiceModel;
import sistersart.model.view.ShoppingCartItem;
import sistersart.repository.OrderRepository;
import sistersart.service.OrderService;
import sistersart.service.ProductService;
import sistersart.service.UserService;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductService productService, UserService userService, ModelMapper modelMapper, Validator validator) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    @Override
    public BigDecimal calcTotal(List<ShoppingCartItem> cart) {
        BigDecimal result = new BigDecimal(0);
        for (ShoppingCartItem item : cart) {
            result = result.add(item
                    .getProduct()
                    .getPrice()
                    .multiply(new BigDecimal(item.getQuantity())));
        }
        return result;
    }

    @Override
    public OrderServiceModel prepareOrder(List<ShoppingCartItem> cart, String customer) {
        OrderServiceModel orderServiceModel = new OrderServiceModel();
        orderServiceModel.setCustomer(this.userService.findUserByUserName(customer));
        Set<OrderProductServiceModel> products = new HashSet<>();
        for (ShoppingCartItem item : cart) {
            OrderProductServiceModel orderProductServiceModel
                    = this.modelMapper.map(item.getProduct(), OrderProductServiceModel.class);
            String productId = orderProductServiceModel.getProduct().getId();
            ProductServiceModel productServiceModel = this.productService.findProductById(productId);
            int productQuantity = productServiceModel.getQuantity();
            if (item.getQuantity() > productQuantity) {
                throw new IllegalArgumentException(String.format("Имаме само %d брой от продукта: %s! Моля коригирайте поръчката от кошницата!",
                        productQuantity, item.getProduct().getProduct().getName()));
            }
        }
        for (ShoppingCartItem item : cart) {
            OrderProductServiceModel orderProductServiceModel
                    = this.modelMapper.map(item.getProduct(), OrderProductServiceModel.class);
            String productId = orderProductServiceModel.getProduct().getId();
            ProductServiceModel productServiceModel = this.productService.findProductById(productId);
            this.productService.decreaseProductQuantity(productId, item.getQuantity(), productServiceModel);
            orderProductServiceModel.setQuantity(item.getQuantity());
            products.add(orderProductServiceModel);
        }
        orderServiceModel.setProducts(products);
        orderServiceModel.setTotalPrice(this.calcTotal(cart));
        return orderServiceModel;
    }

    @Override
    public boolean createOrder(OrderServiceModel orderServiceModel) {
        orderServiceModel.setFinishedOn(LocalDateTime.now());
        this.orderRepository.save(this.modelMapper.map(orderServiceModel, Order.class));
        return true;
    }

    @Override
    public List<OrderServiceModel> findAllOrders() {
        List<Order> orders = this.orderRepository.findAll();
        return orders
                .stream()
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderServiceModel> findOrdersByCustomer(String username) {
        return this.orderRepository.findAllOrdersByCustomer_UsernameOrderByFinishedOn(username)
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel findOrderById(String id) {
        return this.orderRepository.findById(id)
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }
}
