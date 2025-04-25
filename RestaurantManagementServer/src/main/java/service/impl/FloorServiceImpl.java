package service.impl;

import dao.FloorDAO;
import model.FloorEntity;
import service.FloorService;

import java.rmi.RemoteException;

public class FloorServiceImpl extends GenericServiceImpl<FloorEntity, Long> implements FloorService {
    private FloorDAO floorDAO;

    public FloorServiceImpl(FloorDAO floorDAO) throws RemoteException {
        super(floorDAO);
        this.floorDAO = floorDAO;
    }
}