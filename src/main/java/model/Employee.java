package model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")

    private Integer id;
    private String firstName;
    private String lastName;
    private String role;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private String username;
    private String password;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "modified_by")
    private Integer modifiedBy;
    @Column(name = "modified_on")
    private LocalDate modifiedOn;
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Column(name = "created_on")
    private LocalDate createdOn;


    public Integer getId() {
        return id;
    }


}
