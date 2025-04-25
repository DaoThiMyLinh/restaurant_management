package service.impl;
import dao.ItemDAO;
import model.ItemEntity;
import service.ItemService;
import java.rmi.RemoteException;

public class ItemServiceImpl extends GenericServiceImpl<ItemEntity, Long> implements ItemService {
    private ItemDAO itemDAO;
    public ItemServiceImpl(ItemDAO itemDAO) throws RemoteException {
        super(itemDAO);
        this.itemDAO = itemDAO;
    }

    @Override
    public ItemEntity findByName(String name) throws RemoteException {
        return itemDAO.findByName(name);
    }
}
