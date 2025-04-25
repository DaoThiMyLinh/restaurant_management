package service;
import model.EmployeeEntity;

import java.rmi.RemoteException;

public interface EmployeeService extends GenericService<EmployeeEntity, Long> {
    boolean checkAccount(String username, String password) throws RemoteException;
    EmployeeEntity findByUsername(String username) throws RemoteException;
}