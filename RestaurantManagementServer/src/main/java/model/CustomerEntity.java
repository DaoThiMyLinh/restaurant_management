package model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "CustomerEntity.findAll", query = "select c from CustomerEntity c"),
        @NamedQuery(name = "CustomerEntity.findByPhone", query = "select c from CustomerEntity c where c.phone = :phone"),
})
public class CustomerEntity extends BaseEntity implements Serializable {
    @Id
    @Column(name = "customer_id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(name = "name", nullable = false, columnDefinition = "nvarchar(255)")
    private String name;

    @Column(name = "email", nullable = false, columnDefinition = "nvarchar(255)")
    private String email;

    @Column(name = "phone", nullable = false, columnDefinition = "nvarchar(255)")
    private String phone;

    @Embedded
    private Address address;

    @Column(name = "day_of_birth")
    private LocalDateTime dayOfBirth;


    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private Set<OrderEntity> orders;

    public CustomerEntity(String name, String email, String phone, Address address, LocalDateTime dayOfBirth) {
        setName(name);
        setEmail(email);
        setPhone(phone);
//        setAddress(address);
        setDayOfBirth(dayOfBirth);
    }
    
    public CustomerEntity(Long customerId) {
        setCustomerId(customerId);
    }

    public CustomerEntity(Long customerId, String name, String email, String phoneNumber, Address address, LocalDateTime createdDate, LocalDateTime dayOfBirth, Set<OrderEntity> orders) {
        setCustomerId(customerId);
        setName(name);
        setEmail(email);
        setPhone(phoneNumber);
        setAddress(address);
        setCreatedDate(createdDate);
        setDayOfBirth(dayOfBirth);
        setOrders(orders);
    }


}
