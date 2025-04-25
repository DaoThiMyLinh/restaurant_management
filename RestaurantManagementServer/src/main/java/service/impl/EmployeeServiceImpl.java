package service.impl;
import dao.EmployeeDAO;
import model.EmployeeEntity;
import service.EmployeeService;
import java.rmi.RemoteException;

public class EmployeeServiceImpl extends GenericServiceImpl<EmployeeEntity, Long> implements EmployeeService {
    private EmployeeDAO employeeDAO;
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) throws RemoteException {
        super(employeeDAO);
        this.employeeDAO = employeeDAO;
    }

    public boolean checkAccount(String username, String password) throws RemoteException{
        return employeeDAO.checkAccount(username, password);
    }

    @Override
    public EmployeeEntity findByUsername(String username) throws RemoteException {
        return employeeDAO.findByUsername(username);
    }
}