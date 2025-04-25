package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.OrderDetailEntity;
import model.OrderEntity;

public class OrderDAO extends GenericDAO<OrderEntity, Long> {
    public OrderDAO() {
        super(OrderEntity.class);
    }

    public OrderDAO(EntityManager em) {
        super(em, OrderEntity.class);
    }

    @Override
    public boolean save(OrderEntity orderEntity) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();

            if (orderEntity.getOrderDetails() != null) {
                for (OrderDetailEntity detail : orderEntity.getOrderDetails()) {
                    detail.setOrder(orderEntity);
                    detail.setItem(detail.getItem());
                    detail.setLineTotal(); // Tính lại lineTotal nếu cần
                }
            }

            em.persist(orderEntity);

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Rollback nếu có lỗi
            }
            e.printStackTrace();
            return false;
        }
    }

}
