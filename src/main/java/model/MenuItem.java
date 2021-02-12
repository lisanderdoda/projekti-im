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
public class MenuItem extends AbstractEntity{

    private String name;

    private String description;
    @Column(name = "unit_price")
    private double unitPrice;
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
