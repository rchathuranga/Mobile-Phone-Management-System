package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.CustomBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.*;
import lk.ijse.projectmp.dto.CustomDTO;
import lk.ijse.projectmp.entity.Custom;
import lk.ijse.projectmp.entity.Customer;
import lk.ijse.projectmp.entity.Item;
import lk.ijse.projectmp.entity.Phone;

import java.util.ArrayList;

public class CustomBOImpl implements CustomBO {

    private BrandDAO brandDAO= (BrandDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BRAND);
    private CatagoryDAO catagoryDAO= (CatagoryDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CATAGORY);
    private PhoneDAO phoneDAO= (PhoneDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PHONE);
    private ItemDAO itemDAO= (ItemDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ITEM);
    private CustomDAO dao= (CustomDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOM);

    @Override
    public ArrayList<CustomDTO> getOrdersofCustomer(int id) throws Exception {
        ArrayList<CustomDTO> arrayList=new ArrayList<>();
        ArrayList<Custom> detail=dao.getOrdersofCustomer(id);
        for(Custom c:detail){
            CustomDTO customDTO=new CustomDTO();
            customDTO.setOrderId(c.getOrderId());
            customDTO.setCustomerId(c.getcId());
            customDTO.setOrderAmount(c.getOrderAmount());
            customDTO.setDate(c.getDate());
            arrayList.add(customDTO);


        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomDTO> getCustomerInteract() throws Exception {
        ArrayList<CustomDTO> arrayList=new ArrayList<>();
        ArrayList<Custom> custom=dao.getCustomerInteract();

        for(Custom c:custom){
            CustomDTO customDTO=new CustomDTO();
            customDTO.setCustomerId(c.getcId());
            customDTO.setOrderId(c.getOrderId());
            customDTO.setOrderAmount(c.getOrderAmount());
            customDTO.setDate(c.getDate());
            arrayList.add(customDTO);
        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomDTO> getAllItemandPhone() throws Exception {
        ArrayList<Phone> allphone=phoneDAO.getAll();
        ArrayList<Item> allItem=itemDAO.getAll();
        ArrayList<CustomDTO> arrayList=new ArrayList<>();

        
        for (Phone p :allphone) {
            String brand=brandDAO.search(p.getBrandId()).getName();
            CustomDTO phone=new CustomDTO(p.getId(),
                    p.getBrandId(),0,p.getName(),
                    p.getRam(),p.getStorage(),p.getNetwork(),
                    p.getQty(),p.getWarranty(),p.getSellingPrice(),
                    p.getBuyingPrice()
            );
            phone.setBrandName(brand);
            arrayList.add(phone);
        }

        for (Item i:allItem){
            String catagory=catagoryDAO.search(i.getCataid()).getName();
            String brand=brandDAO.search(i.getBrandid()).getName();
            CustomDTO item=new CustomDTO(
                    i.getId(),i.getBrandid(),
                    i.getCataid(),i.getName(),
                    i.getQty(),i.getWarranty(),
                    i.getSellingPrice(),
                    i.getBuyingPrice()
            );
            item.setBrandName(brand);
            item.setCatagoryName(catagory);

            arrayList.add(item);
        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomDTO> getMostSelling() throws Exception {
        ArrayList<Custom> mostSelling = dao.getMostSelling();
        ArrayList<CustomDTO> arrayList=new ArrayList<>();

        for (Custom c :
                mostSelling) {
            arrayList.add(new CustomDTO(c.getId(), c.getName(), c.getQty(), c.getSellingPrice()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomDTO> getRepairDetail() throws Exception {
        return null;
    }

    @Override
    public ArrayList<CustomDTO> getRepairsofCustomer(int id) throws Exception {
        ArrayList<CustomDTO> arrayList=new ArrayList<>();
        ArrayList<Custom> details=dao.getRepairsofCustomer(id);
        for(Custom c:details){
            CustomDTO customDTO=new CustomDTO();
            customDTO.setCustomerId(c.getcId());
            customDTO.setOrderId(c.getOrderId());
            customDTO.setOrderAmount(c.getOrderAmount());
            customDTO.setDate(c.getDate());
            arrayList.add(customDTO);
        }
        return arrayList;
    }

    @Override
    public ArrayList<CustomDTO> getLeastSelling() throws Exception {
        ArrayList<CustomDTO> arrayList=new ArrayList<>();
        ArrayList<Custom> leastSelling=dao.getLeastSelling();
        for (Custom c :
                leastSelling) {
            arrayList.add(new CustomDTO(c.getId(), c.getName(), c.getQty(), c.getSellingPrice()));
        }
        return arrayList;
    }

    @Override
    public CustomDTO getProfitDetail() throws Exception{
        double orderIncome=dao.getOrderIncome();
        double repairIncome=dao.getRepairIncome();
        double totIncome=orderIncome+repairIncome;
        double phoneExpence=dao.getPhoneExpense();
        double itemExpence=dao.getItemExpense();
        double partExpense=dao.getRepairPartExpence();

        double totExpence=phoneExpence+itemExpence+partExpense;
        double profit=totIncome-totExpence;

        return new CustomDTO(orderIncome,repairIncome,totIncome,phoneExpence,itemExpence,partExpense,totExpence,profit);
    }

}
