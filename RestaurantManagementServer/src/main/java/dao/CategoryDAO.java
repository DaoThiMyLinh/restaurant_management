package dao;

import jakarta.persistence.EntityManager;
import model.CategoryEntity;

public class CategoryDAO extends GenericDAO<CategoryEntity, Long> {
    public CategoryDAO() {
        super(CategoryEntity.class);
    }

    public CategoryDAO(EntityManager em) {
        super(em, CategoryEntity.class);
    }
}
