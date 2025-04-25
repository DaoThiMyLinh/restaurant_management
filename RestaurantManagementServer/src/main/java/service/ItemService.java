package service;
import model.ItemEntity;

import java.rmi.RemoteException;

public interface ItemService extends GenericService<ItemEntity, Long> {
    ItemEntity findByName(String name) throws RemoteException;
}