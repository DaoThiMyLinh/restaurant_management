package dao;

import jakarta.persistence.EntityManager;
import model.ItemEntity;

public class ItemDAO extends GenericDAO<ItemEntity, Integer> {
    public ItemDAO() {
        super(ItemEntity.class);
    }

    public ItemDAO(EntityManager em) {
        super(em, ItemEntity.class);
    }
}
