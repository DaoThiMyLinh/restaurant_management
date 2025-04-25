package service.impl;
import dao.RoleDAO;
import model.RoleEntity;
import service.RoleService;
import java.rmi.RemoteException;

public class RoleServiceImpl extends GenericServiceImpl<RoleEntity, Long> implements RoleService {
    private RoleDAO roleDAO;
    public RoleServiceImpl(RoleDAO roleDAO) throws RemoteException {
        super(roleDAO);
        this.roleDAO = roleDAO;
    }
}