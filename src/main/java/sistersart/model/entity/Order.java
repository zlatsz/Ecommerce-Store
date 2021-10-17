package sistersart.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    private User user;
    private List<OrderProduct> products;
    private BigDecimal totalPrice;
    private LocalDateTime finishedOn;

    public Order() {
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getCustomer() {
        return this.user;
    }

    public void setCustomer(User customer) {
        this.user = customer;
    }


    @ManyToMany(targetEntity = OrderProduct.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "orders_products", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    public List<OrderProduct> getProducts() {
        return this.products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    @Column(name = "total_price")
    public BigDecimal getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "finished_on")
    public LocalDateTime getFinishedOn() {
        return this.finishedOn;
    }

    public void setFinishedOn(LocalDateTime finishedOn) {
        this.finishedOn = finishedOn;
    }
}
