package model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class OrderDetailId implements Serializable {

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "order_id")
    private Long orderId;

    public OrderDetailId() {
    }

    public OrderDetailId(Long itemId, Long orderId) {
        this.itemId = itemId;
        this.orderId = orderId;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetailId that = (OrderDetailId) o;
        return Objects.equals(itemId, that.itemId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, orderId);
    }

    @Override
    public String toString() {
        return "OrderDetailId{" + "itemId=" + itemId + ", orderId=" + orderId;
    }

}
