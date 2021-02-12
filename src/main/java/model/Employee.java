package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Data
public class Employee extends AbstractEntity{

    private String firstName;
    private String lastName;
    private String role;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @ToString.Exclude
    private String username;
    @ToString.Exclude
    private String password;
    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders = new HashSet<>();


}
