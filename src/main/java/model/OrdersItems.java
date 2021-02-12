package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "orders_items")
@Data
public class OrdersItems extends AbstractEntity{
    private Integer quantity;
    private Double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MenuItem menuItem;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;



}
