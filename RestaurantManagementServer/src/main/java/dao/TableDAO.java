package dao;

import jakarta.persistence.EntityManager;
import model.TableEntity;

public class TableDAO extends GenericDAO<TableEntity, Integer> {
    public TableDAO() {
        super(TableEntity.class);
    }

    public TableDAO(EntityManager em) {
        super(em, TableEntity.class);
    }
}
