package model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "orders_items")
@Data
public class OrdersItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_item_id")
    private Integer ordersItemId;
    private Integer quantity;
    private Double price;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "modified_by")
    private Integer modifiedBy;
    @Column(name = "modified_on")
    private LocalDate modifiedOn;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "created_on")
    private LocalDate createdOn;


}
