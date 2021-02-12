package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@javax.persistence.Table(name = "tables")
@Data
public class Table extends AbstractEntity {

    private String name;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;
    @Column(name = "is_occupied")
    private boolean isOccupied;
    @OneToMany(mappedBy = "table")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders = new HashSet<>();
}
