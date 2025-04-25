package service.impl;
import dao.OrderDAO;
import model.OrderEntity;
import service.OrderService;
import java.rmi.RemoteException;

public class OrderServiceImpl extends GenericServiceImpl<OrderEntity, Long> implements OrderService {
    private OrderDAO orderDAO;
    public OrderServiceImpl(OrderDAO orderDAO) throws RemoteException {
        super(orderDAO);
        this.orderDAO = orderDAO;
    }
}