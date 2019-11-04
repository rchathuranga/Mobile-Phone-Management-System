package lk.ijse.projectmp.dao;

import lk.ijse.projectmp.dao.dao.custom.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }

    public static DAOFactory getInstance(){
        if(daoFactory==null){
            daoFactory=new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,ITEM,PHONE,ACCOUNT,ORDERS,CATAGORY,BRAND,CUSTOM,REPAIRPART,
        SDISCOUNT,RETURNS,REPAIR,REPAIRSERVICE,REPAIRDETAIL,ORDERDETAIL
    }

    public SuperDAO getDao(DAOTypes type){
        switch (type){
            case CUSTOMER:return new CustomerDAOImpl();
            case ACCOUNT:return new AccountDAOImpl();
            case CATAGORY:return new CatagoryDAOImpl();
            case BRAND:return new BrandDAOImpl();
            case CUSTOM:return new CustomDAOImpl();
            case SDISCOUNT:return new SDiscountDAOImpl();
            case ORDERS:return new OrdersDAOImpl();
            case PHONE:return new PhoneDAOImpl();
            case ITEM:return new ItemDAOImpl();
            case RETURNS:return new ReturnsDAOImpl();
            case REPAIR:return new RepairDAOImpl();
            case REPAIRSERVICE:return new RepairServiceDAOImpl();
            case REPAIRDETAIL:return new RepairDetailDAOImpl();
            case ORDERDETAIL:return new OrderDetailDAOImpl();
            case REPAIRPART:return new RepairPartDAOImpl();
            default:return null;
        }
    }
}
