package rmi;

import dao.CustomerDAO;
import dao.ProductDAO;
import model.Customer;
import model.Product;
import service.CustomerService;
import service.ProductService;
import service.impl.CustomerServiceImpl;
import service.impl.ProductServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer {

    public static void main(String[] args) throws Exception{

        Context context = new InitialContext();
        LocateRegistry.createRegistry(9090);

        CustomerDAO customerDAO = new CustomerDAO(Customer.class); //Java Object
        ProductDAO productDAO = new ProductDAO(Product.class); //Java Object

        CustomerService customerService = new CustomerServiceImpl(customerDAO); //Java Remote Object
        ProductService productService = new ProductServiceImpl(productDAO); //Java Remote Object

        context.bind("rmi://MSI:9090/customerService", customerService);
        context.bind("rmi://MSI:9090/productService", productService);

        System.out.println("RMI Server is running...");
    }
}
