package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.OrderDetailBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.*;
import lk.ijse.projectmp.dto.OrderDetailDTO;
import lk.ijse.projectmp.dto.OrdersDTO;
import lk.ijse.projectmp.entity.OrderDetail;
import lk.ijse.projectmp.entity.Orders;

import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    private CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOMER);
    private OrdersDAO ordersDAO= (OrdersDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ORDERS);
    private OrderDetailDAO dao= (OrderDetailDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ORDERDETAIL);
    private ItemDAO itemDAO= (ItemDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ITEM);
    private PhoneDAO phoneDAO= (PhoneDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PHONE);

    @Override
    public ArrayList<OrderDetailDTO> getAll() throws Exception {
        ArrayList<OrderDetail> all=dao.getAll();
        ArrayList<OrderDetailDTO> arrayList=new ArrayList<>();

        for (OrderDetail od :all) {
            OrderDetailDTO odDTO=new OrderDetailDTO(
                    od.getId(),
                    od.getOrderid(),
                    od.getItemid(),
                    od.getPhoneid(),
                    od.getSdiscountid(),
                    od.getQty(),
                    od.getTotal(),
                    od.getImei()
            );
            int cid = ordersDAO.search(od.getOrderid()).getCid();
            odDTO.setCustomerName(customerDAO.search(cid).getName());
            odDTO.setCustomerContact(customerDAO.search(cid).getContact());
            odDTO.setDiscount(ordersDAO.search(od.getOrderid()).getDiscount());
            arrayList.add(odDTO);
        }

        return arrayList;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAll(int orderId) throws Exception {
        ArrayList<OrderDetail> all=dao.searchAll(orderId);
        ArrayList<OrderDetailDTO> arrayList=new ArrayList<>();

        for (OrderDetail od :all) {
            OrderDetailDTO odDTO=new OrderDetailDTO(
                    od.getId(),
                    od.getOrderid(),
                    od.getItemid(),
                    od.getPhoneid(),
                    od.getSdiscountid(),
                    od.getQty(),
                    od.getAmount(),
                    od.getDiscount(),
                    od.getTotal(),
                    od.getImei()
            );

            int cid = ordersDAO.search(od.getOrderid()).getCid();
            odDTO.setCustomerName(customerDAO.search(cid).getName());
            odDTO.setCustomerContact(customerDAO.search(cid).getContact());
            odDTO.setDiscount(ordersDAO.search(od.getOrderid()).getDiscount());
            if(odDTO.getItemid()!=0){
                odDTO.setItemName(itemDAO.search(odDTO.getItemid()).getName());
            }
            if (odDTO.getPhoneid()!=0){
                odDTO.setItemName(phoneDAO.search(odDTO.getPhoneid()).getName());
            }
            arrayList.add(odDTO);
        }

        return arrayList;
    }

    @Override
    public ArrayList<OrderDetailDTO> getAllToday() throws Exception {
        ArrayList<OrderDetailDTO> arrayList=new ArrayList<>();
        ArrayList<OrderDetail> all = dao.getAll();
        for (OrderDetail od :all) {
            OrderDetailDTO odDTO=new OrderDetailDTO(
                    od.getId(),
                    od.getOrderid(),
                    od.getItemid(),
                    od.getPhoneid(),
                    od.getSdiscountid(),
                    od.getQty(),
                    od.getTotal(),
                    od.getImei()
            );
            int cid = ordersDAO.search(od.getOrderid()).getCid();
            odDTO.setCustomerName(customerDAO.search(cid).getName());
            odDTO.setCustomerContact(customerDAO.search(cid).getContact());
            odDTO.setDiscount(ordersDAO.search(od.getOrderid()).getDiscount());
            arrayList.add(odDTO);
        }

        return arrayList;
    }
}
