package lk.ijse.projectmp.bo;

import lk.ijse.projectmp.bo.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){
    }
   public static BOFactory getInstance(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
   }

   public enum BOTypes{
        CUSTOMER,ACCOUNT,ORDERS,ITEM,PHONE,BRAND,CATAGORY,CUSTOM,
       SDISCOUNT,RETURNS,REPAIRSERVICE,REPAIR,REPAIRDETAIL,ORDERDETAIL
   }

   public SuperBO getBO(BOTypes type){
        switch (type){
            case CUSTOMER:return new CustomerBOImpl();
            case ACCOUNT:return new AccountBOImpl();
            case BRAND:return new BrandBOImpl();
            case CATAGORY:return new CatagoryBOImpl();
            case CUSTOM:return new CustomBOImpl();
            case SDISCOUNT:return new SDiscountBOImpl();
            case ORDERS:return new OrdersBOImpl();
            case PHONE: return new PhoneBOImpl();
            case ITEM:return new ItemBOImpl();
            case RETURNS:return new ReturnsBOImpl();
            case REPAIRSERVICE:return new RepairServiceBOImpl();
            case REPAIR:return new RepairBOImpl();
            case REPAIRDETAIL:return new RepairDetailBOImpl();
            case ORDERDETAIL:return new OrderDetailBOImpl();
            default:return null;
        }
   }
}
