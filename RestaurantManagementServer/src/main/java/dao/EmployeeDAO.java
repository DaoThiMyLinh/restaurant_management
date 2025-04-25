package dao;

import jakarta.persistence.EntityManager;
import model.EmployeeEntity;

public class EmployeeDAO extends GenericDAO<EmployeeEntity, Integer> {
    public EmployeeDAO() {
        super(EmployeeEntity.class);
    }

    public EmployeeDAO(EntityManager em) {
        super(em, EmployeeEntity.class);
    }
}
