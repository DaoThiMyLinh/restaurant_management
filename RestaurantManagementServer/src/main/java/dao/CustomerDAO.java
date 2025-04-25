package dao;

import jakarta.persistence.EntityManager;
import model.CustomerEntity;

public class CustomerDAO extends GenericDAO<CustomerEntity, Long> {
    public CustomerDAO() {
        super(CustomerEntity.class);
    }

    public CustomerDAO(EntityManager em) {
        super(em, CustomerEntity.class);
    }

    public CustomerEntity findByPhone(String phone) {
        String jpql = "SELECT c FROM CustomerEntity c WHERE c.phone = :phone";
        try {
            return em.createQuery(jpql, CustomerEntity.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

}
