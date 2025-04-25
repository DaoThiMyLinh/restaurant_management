package dao;

import jakarta.persistence.EntityManager;
import model.CustomerEntity;

public class CustomerDAO extends GenericDAO<CustomerEntity, Integer> {
    public CustomerDAO() {
        super(CustomerEntity.class);
    }

    public CustomerDAO(EntityManager em) {
        super(em, CustomerEntity.class);
    }
}
