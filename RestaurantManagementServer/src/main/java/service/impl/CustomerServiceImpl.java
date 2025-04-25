package service.impl;

import dao.CustomerDAO;
import model.CustomerEntity;
import service.CustomerService;

import java.rmi.RemoteException;

public class CustomerServiceImpl extends GenericServiceImpl<CustomerEntity, Integer> implements CustomerService {
    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) throws RemoteException {
        super(customerDAO);
        this.customerDAO = customerDAO;
    }
}
