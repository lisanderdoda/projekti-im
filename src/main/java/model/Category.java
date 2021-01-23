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
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer id;
    private String name;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "last_modified_on")
    private LocalDateTime modifiedOn;
    @Column(name = "last_modified_by")
    private Integer modifiedBy;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "created_on")
    private LocalDateTime createdOn;
    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<MenuItem> menuItems = new HashSet<>();


}
