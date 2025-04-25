package dao;

import jakarta.persistence.EntityManager;
import model.EmployeeEntity;

public class EmployeeDAO extends GenericDAO<EmployeeEntity, Long> {
    public EmployeeDAO() {
        super(EmployeeEntity.class);
    }

    public EmployeeDAO(EntityManager em) {
        super(em, EmployeeEntity.class);
    }

    public boolean checkAccount(String username, String password) {
        String jpql = "SELECT COUNT(e) FROM EmployeeEntity e WHERE e.username = :username AND e.password = :pass";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("username", username)
                .setParameter("pass", password)
                .getSingleResult();
        return count > 0;
    }

    public EmployeeEntity findByUsername(String username) {
        String jpql = "SELECT e FROM EmployeeEntity e WHERE e.username = :username";
        try {
            return em.createQuery(jpql, EmployeeEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException ex) {
            return null;
        }
    }


}
