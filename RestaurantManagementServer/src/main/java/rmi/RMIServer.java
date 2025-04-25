package rmi;

import dao.*;
import service.*;
import service.impl.*;
import util.JPAUtil;
import util.HostNameUtil;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

import static util.HostNameUtil.getHostName;


public class RMIServer {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(9090);

        var em = JPAUtil.getEntityManager();

        // 3. Khởi tạo các DAO
        CategoryDAO categoryDAO = new CategoryDAO(em);
        ItemDAO itemDAO = new ItemDAO(em);
        FloorDAO floorDAO = new FloorDAO(em);
        TableDAO tableDAO = new TableDAO(em);
        CustomerDAO customerDAO = new CustomerDAO(em);
        OrderDAO orderDAO = new OrderDAO(em);
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO(em);
        EmployeeDAO employeeDAO = new EmployeeDAO(em);
        RoleDAO roleDAO = new RoleDAO(em);


        CategoryService categoryService = new CategoryServiceImpl(categoryDAO);
        ItemService itemService = new ItemServiceImpl(itemDAO);
        FloorService floorService = new FloorServiceImpl(floorDAO);
        TableService tableService = new TableServiceImpl(tableDAO);
        CustomerService customerService = new CustomerServiceImpl(customerDAO);
        OrderService orderService = new OrderServiceImpl(orderDAO);
        OrderDetailService orderDetailService = new OrderDetailServiceImpl(orderDetailDAO);
        EmployeeService employeeService = new EmployeeServiceImpl(employeeDAO);
        RoleService roleService = new RoleServiceImpl(roleDAO);

        context.bind(HostNameUtil.getURI(getHostName(), CategoryService.class), categoryService);
        context.bind(HostNameUtil.getURI(getHostName(), ItemService.class), itemService);
        context.bind(HostNameUtil.getURI(getHostName(), FloorService.class), floorService);
        context.bind(HostNameUtil.getURI(getHostName(), TableService.class), tableService);
        context.bind(HostNameUtil.getURI(getHostName(), CustomerService.class), customerService);
        context.bind(HostNameUtil.getURI(getHostName(), OrderService.class), orderService);
        context.bind(HostNameUtil.getURI(getHostName(), OrderDetailService.class), orderDetailService);
        context.bind(HostNameUtil.getURI(getHostName(), EmployeeService.class), employeeService);
        context.bind(HostNameUtil.getURI(getHostName(), RoleService.class), roleService);

        System.out.println("RMI Server is running ");
    }

}
