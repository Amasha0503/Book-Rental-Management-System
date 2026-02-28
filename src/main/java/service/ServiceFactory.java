package service;

import service.custom.impl.BookServiceImpl;
import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.RentalServiceImpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType serviceType){
        switch (serviceType){
            case CUSTOMER:return (T) new CustomerServiceImpl();
            case BOOK:return (T) new BookServiceImpl();
            case RENTAL:return (T) new RentalServiceImpl();
        }
        return null;
    }
}
