package service;

import model.CustomerEntity;

import java.rmi.RemoteException;

public interface CustomerService extends GenericService<CustomerEntity, Long> {

    CustomerEntity findByPhone(String phone) throws RemoteException;
}
