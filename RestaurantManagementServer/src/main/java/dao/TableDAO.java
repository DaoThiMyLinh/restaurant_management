package dao;

import jakarta.persistence.EntityManager;
import model.TableEntity;
import model.enums.TableStatusEnum;

import java.rmi.RemoteException;
import java.util.List;

public class TableDAO extends GenericDAO<TableEntity, Long> {
    public TableDAO() {
        super(TableEntity.class);
    }

    public TableDAO(EntityManager em) {
        super(em, TableEntity.class);
    }

    public List<TableEntity> getByFloor(Long floorId) {
        String jpql = "SELECT t FROM TableEntity t WHERE t.floor.floorId = :id";
        return em.createQuery(jpql, TableEntity.class)
                .setParameter("id", floorId)
                .getResultList();
    }

    public List<TableEntity> getAvailableByFloor(Long floorId) {
        String jpql = "SELECT t FROM TableEntity t WHERE t.floor.floorId = :id AND t.tableStatus = :status";
        return em.createQuery(jpql, TableEntity.class)
                .setParameter("id", floorId)
                .setParameter("status", TableStatusEnum.AVAILABLE)
                .getResultList();
    }

    public TableEntity findByName(String name) {
        String jpql = "SELECT t FROM TableEntity t WHERE t.name = :name";
        try {
            return em.createQuery(jpql, TableEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }




}
