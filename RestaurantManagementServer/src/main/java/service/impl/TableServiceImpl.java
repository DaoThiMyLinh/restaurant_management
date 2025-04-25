package service.impl;

import dao.TableDAO;
import model.TableEntity;
import service.TableService;
import service.impl.GenericServiceImpl;

import java.rmi.RemoteException;
import java.util.List;

public class TableServiceImpl extends GenericServiceImpl<TableEntity, Long> implements TableService {
    private TableDAO tableDAO;
    public TableServiceImpl(TableDAO tableDAO) throws RemoteException {
        super(tableDAO);
        this.tableDAO = tableDAO;
    }

    @Override
    public List<TableEntity> getTablesByFloor(Long floorId) throws RemoteException {
        return tableDAO.getByFloor(floorId);
    }

    public List<TableEntity> getAvailableByFloor(Long floorId) throws RemoteException {
        return tableDAO.getAvailableByFloor(floorId);
    }

    @Override
    public TableEntity findByName(String name) throws RemoteException {
        return tableDAO.findByName(name);
    }

}