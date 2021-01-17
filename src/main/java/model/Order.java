package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
}
