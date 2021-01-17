package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@javax.persistence.Table(name = "tables")
@Data
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
private Integer id;
private String name;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;
    private String state;
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
    @OneToMany(mappedBy = "table")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders= new HashSet<>();


}
