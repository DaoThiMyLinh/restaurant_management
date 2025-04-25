package service.impl;

import dao.CustomerDAO;
import model.CustomerEntity;
import service.CustomerService;

import java.rmi.RemoteException;

public class CustomerServiceImpl extends GenericServiceImpl<CustomerEntity, Long> implements CustomerService {
    private CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) throws RemoteException {
        super(customerDAO);
        this.customerDAO = customerDAO;
    }

    @Override
    public CustomerEntity findByPhone(String phone) throws RemoteException {
        return customerDAO.findByPhone(phone);
    }
}
