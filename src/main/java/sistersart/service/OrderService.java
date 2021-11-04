package sistersart.service;


import sistersart.model.service.OrderServiceModel;
import sistersart.model.view.ShoppingCartItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    BigDecimal calcTotal(List<ShoppingCartItem> cart);

    OrderServiceModel prepareOrder(List<ShoppingCartItem> cart, String customer);

    boolean createOrder(OrderServiceModel orderServiceModel);

    List<OrderServiceModel> findAllOrders();

    List<OrderServiceModel> findOrdersByCustomer(String username);

    OrderServiceModel findOrderById(String id);

}
