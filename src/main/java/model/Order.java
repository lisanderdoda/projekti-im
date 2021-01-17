package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @Column(name = "order_id")
    private Integer orderId;


    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "last_modified_on")
    private LocalDateTime modifiedOn;
    @Column(name = "last_modified_by")
    private LocalDateTime modifiedBy;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @Column(name = "paid_on")
    private LocalDateTime paidOn;
    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrdersItems> ordersItems = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private model.Table table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Payment payment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Employee employee;

}
