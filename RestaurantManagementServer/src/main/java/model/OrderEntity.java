/*
 * @ (#) OrderEntity.java      1.0      1/16/2025
 *
 * Copyright (c) 2025 IUH. ALL rights reserved.
 */
package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.enums.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/*
 * @description:
 * @author: Hoang Huy
 * @date: 1/16/2025
 * @version: 1.0
 */
@Data
@Entity
@Table(name = "orders")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NamedQueries({
    @NamedQuery(name = "OrderEntity.findAll", query = "select o from OrderEntity o")
})
public class OrderEntity extends BaseEntity implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, columnDefinition = "nvarchar(50)")
    private PaymentStatusEnum paymentStatus;

    @ToString.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderDetailEntity> orderDetails;

    @Override
    @Column(name = "order_date", nullable = false, updatable = false)
    public LocalDateTime getCreatedDate() {
        return super.getCreatedDate();
    }

    public OrderEntity() {
        this.paymentStatus = PaymentStatusEnum.UNPAID;
        this.orderDetails = new HashSet<>();
        setTotalPrice();
    }

    public OrderEntity(Long orderId) {
        setOrderId(orderId);
    }

    public OrderEntity(Long orderId, double totalPrice, CustomerEntity customer, EmployeeEntity employee,
                       TableEntity table, PaymentStatusEnum paymentStatus, Set<OrderDetailEntity> orderDetails) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.employee = employee;
        this.table = table;
        this.paymentStatus = paymentStatus;
        this.orderDetails = orderDetails;
    }

    public void setTotalPrice() {
        if (orderDetails == null) {
            this.totalPrice = 0;
        } else {
            this.totalPrice = this.orderDetails.stream()
                    .mapToDouble(OrderDetailEntity::getLineTotal)
                    .sum();
        }
    }


    public boolean insertOrderDetail(OrderDetailEntity orderDetail) {
        Set<OrderDetailEntity> orderDetails = this.getOrderDetails();

        Optional<OrderDetailEntity> existingOrderDetail = orderDetails.stream()
                .filter(od -> od.equals(orderDetail))
                .findFirst();

        if (existingOrderDetail.isPresent()) {
            existingOrderDetail.get().setQuantity(
                    existingOrderDetail.get().getQuantity() + orderDetail.getQuantity()
            );
        } else {
            orderDetails.add(orderDetail);
        }

        this.setOrderDetails(orderDetails);

        return true;
    }
}
