package service.impl;

import dao.ProductDAO;
import model.Product;
import service.ProductService;

import java.rmi.RemoteException;
import java.util.List;

public class ProductServiceImpl extends GenericServiceImpl<Product, Integer> implements ProductService {

    private ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) throws RemoteException{
        super(productDAO);
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> listProductsNotBeenSold() throws RemoteException {
        return productDAO.listProductsNotBeenSold();
    }

    @Override
    public List<Product> listProductsWithTheHighestPrice() throws RemoteException {
        return productDAO.listProductsWithTheHighestPrice();
    }
}
