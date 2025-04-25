package dao;

import jakarta.persistence.EntityManager;
import model.OrderDetailEntity;
import model.OrderDetailId;

public class OrderDetailDAO extends GenericDAO<OrderDetailEntity, OrderDetailId> {
    public OrderDetailDAO() {
        super(OrderDetailEntity.class);
    }

    public OrderDetailDAO(EntityManager em) {
        super(em, OrderDetailEntity.class);
    }
}
