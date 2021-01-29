package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menu_items")
@Data
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private Integer menuItemId;

    private String name;

    private String description;
    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "last_modified_on")
    private LocalDateTime modifiedOn;
    @Column(name = "modified_by")
    private Integer modifiedBy;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;
    @OneToMany(mappedBy = "menuItem")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrdersItems> ordersItems= new HashSet<>();
}
