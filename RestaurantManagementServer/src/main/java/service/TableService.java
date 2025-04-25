package service;
import model.TableEntity;

import java.rmi.RemoteException;
import java.util.List;

public interface TableService extends GenericService<TableEntity, Long> {
    List<TableEntity> getTablesByFloor(Long floorId) throws RemoteException;
    List<TableEntity> getAvailableByFloor(Long floorId) throws RemoteException;
    TableEntity findByName(String name) throws RemoteException;
}