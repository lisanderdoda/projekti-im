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
@Table(name = "payments")
@Data
public class Payment extends AbstractEntity{
    @Column(name = "payment_type")
    private String paymentType;
    @OneToMany(mappedBy = "payment")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders = new HashSet<>();


}
