package service.impl;

import dao.CategoryDAO;
import model.CategoryEntity;
import model.CustomerEntity;
import service.CategoryService;
import service.CustomerService;

import java.rmi.RemoteException;

/*
 * @description: CategoryServiceImpl
 * @author: Trần Ngọc Huyền
 * @date: 4/25/2025
 * @version: 1.0
 */
public class CategoryServiceImpl extends GenericServiceImpl<CategoryEntity, Long> implements CategoryService {
    private CategoryDAO categoryDAO;
    public CategoryServiceImpl(CategoryDAO categoryDAO) throws RemoteException {
        super(categoryDAO);
        this.categoryDAO = categoryDAO;
    }
}
