package model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

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
    @Column(name = "modified_on")
private LocalDateTime modifiedOn;
    @Column(name = "modified_by")
    private LocalDateTime modifiedBy;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "created_on")
    private LocalDateTime createdOn;


}
