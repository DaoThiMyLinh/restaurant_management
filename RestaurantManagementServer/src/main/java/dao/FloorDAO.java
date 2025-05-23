package dao;

import jakarta.persistence.EntityManager;
import model.FloorEntity;

public class FloorDAO extends GenericDAO<FloorEntity, Long> {
    public FloorDAO() {
        super(FloorEntity.class);
    }

    public FloorDAO(EntityManager em) {
        super(em, FloorEntity.class);
    }
}
