package dao;

import jakarta.persistence.EntityManager;
import model.ItemEntity;

public class ItemDAO extends GenericDAO<ItemEntity, Long> {
    public ItemDAO() {
        super(ItemEntity.class);
    }

    public ItemDAO(EntityManager em) {
        super(em, ItemEntity.class);
    }

    public ItemEntity findByName(String name) {
        String jpql = "SELECT i FROM ItemEntity i WHERE i.name = :name";
        try {
            return em.createQuery(jpql, ItemEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

}
