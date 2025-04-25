package dao;

import jakarta.persistence.EntityManager;
import model.RoleEntity;

public class RoleDAO extends GenericDAO<RoleEntity, Long> {
    public RoleDAO() {
        super(RoleEntity.class);
    }

    public RoleDAO(EntityManager em) {
        super(em, RoleEntity.class);
    }
}
