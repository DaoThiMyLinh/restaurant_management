package dao;

import jakarta.persistence.EntityManager;
import model.OrderEntity;

public class OrderDAO extends GenericDAO<OrderEntity, Integer> {
    public OrderDAO() {
        super(OrderEntity.class);
    }

    public OrderDAO(EntityManager em) {
        super(em, OrderEntity.class);
    }
}
