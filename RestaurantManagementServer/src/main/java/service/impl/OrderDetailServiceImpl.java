package service.impl;

import dao.OrderDetailDAO;
import model.OrderDetailEntity;
import model.OrderDetailId;
import service.OrderDetailService;

import java.rmi.RemoteException;

public class OrderDetailServiceImpl extends GenericServiceImpl<OrderDetailEntity, OrderDetailId> implements OrderDetailService {
    private OrderDetailDAO orderDetailDAO;

    public OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO) throws RemoteException {
        super(orderDetailDAO);
        this.orderDetailDAO = orderDetailDAO;
    }
}