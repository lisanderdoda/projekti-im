package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {
    @Id
    @Column(name = "menu_item_id")
    private Integer menuItemId;

    private String name;

    private String description;
    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;
    @Column(name = "modified_by")
    private LocalDateTime modifiedBy;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
}
