/*
 * @ (#) OrderDetailEntity.java      1.0      1/16/2025
 *
 * Copyright (c) 2025 IUH. ALL rights reserved.
 */
package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * @description:
 * @author: Hoang Huy
 * @date: 1/16/2025
 * @version: 1.0
 */

@NoArgsConstructor
@Data
@Entity
@Table(name = "order_details")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NamedQueries({
        @NamedQuery(name = "OrderDetailEntity.findAll", query = "SELECT o FROM OrderDetailEntity o"),
        @NamedQuery(name = "OrderDetailEntity.findByOrderId", query = "SELECT o FROM OrderDetailEntity o WHERE o.order.orderId = :orderId"),
})
public class OrderDetailEntity extends BaseEntity implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

    @Id
    @EqualsAndHashCode.Include
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "line_total", nullable = false)
    private double lineTotal;

    @Column
    private double discount;

    @Column(name = "description", columnDefinition = "nvarchar(50)")
    private String description;

    public OrderDetailEntity(int quantity, String description, ItemEntity item, OrderEntity order) {
        this.quantity = quantity;
        this.item = item;
        this.order = order;
        setDescription(description);
        setLineTotal();
    }


    public void setLineTotal() {
        if(this.item != null) {
            this.lineTotal = item.getSellingPrice() * this.quantity;
        } else {
            this.lineTotal = 0;
        }
    }

}
