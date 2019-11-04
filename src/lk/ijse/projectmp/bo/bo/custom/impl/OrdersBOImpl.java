package lk.ijse.projectmp.bo.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.projectmp.bo.bo.custom.OrdersBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.CustomerDAO;
import lk.ijse.projectmp.dao.dao.custom.OrderDetailDAO;
import lk.ijse.projectmp.dao.dao.custom.OrdersDAO;
import lk.ijse.projectmp.dao.dao.custom.dao.custom.impl.ItemDAOImpl;
import lk.ijse.projectmp.dao.dao.custom.dao.custom.impl.PhoneDAOImpl;
import lk.ijse.projectmp.db.DBConnection;
import lk.ijse.projectmp.dto.OrderDetailDTO;
import lk.ijse.projectmp.dto.OrdersDTO;
import lk.ijse.projectmp.entity.Item;
import lk.ijse.projectmp.entity.OrderDetail;
import lk.ijse.projectmp.entity.Orders;
import lk.ijse.projectmp.entity.Phone;

import java.sql.Connection;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {
    private OrdersDAO dao = (OrdersDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ORDERS);
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOMER);
    private OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ORDERDETAIL);
    private PhoneDAOImpl phoneDAO = (PhoneDAOImpl) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PHONE);
    private ItemDAOImpl itemDAO = (ItemDAOImpl) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addOrders(OrdersDTO o) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            boolean isUpdated = false;
            connection.setAutoCommit(false);
            boolean isAdded = dao.add(new Orders(o.getCid(), o.getAmount(), o.getDiscount(), o.getTotal()));
            if (isAdded) {
                Orders lastOrder = dao.getLastOrder();

                for (OrderDetailDTO ordersDTO : o.getOrderDetails()) {
                    boolean isDetailsAdded = orderDetailDAO.add(
                            new OrderDetail(lastOrder.getId(), ordersDTO.getItemid(),
                                    ordersDTO.getPhoneid(), ordersDTO.getSdicountid(),
                                    ordersDTO.getQty(), ordersDTO.getAmount(),
                                    ordersDTO.getDiscount(), ordersDTO.getTotal(),
                                    ordersDTO.getImei()
                            ));

                    if (isDetailsAdded) {
                        if (ordersDTO.getItemid() == 0) {
                            Phone p = new Phone();
                            p.setId(ordersDTO.getPhoneid());
                            p.setQty(ordersDTO.getQty());
                            isUpdated = phoneDAO.updateQty(p);
                        } else {
                            Item i = new Item();
                            i.setId(ordersDTO.getItemid());
                            i.setQty(ordersDTO.getQty());
                            isUpdated = itemDAO.updateItemQty(i);
                        }
                    }
                }
                if (isUpdated) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public OrdersDTO searchOrders(int id) throws Exception {
        Orders search = dao.search(id);
        OrdersDTO ordersDTO = new OrdersDTO(search.getId(), search.getCid(), search.getDate(), search.getTime(), search.getAmount(), search.getDiscount(), search.getTotal());
        ordersDTO.setCustomerName(customerDAO.search(ordersDTO.getCid()).getName());
        return ordersDTO;
    }

    @Override
    public ArrayList<OrdersDTO> getAllOrders()  {
        return null;
    }

    @Override
    public ArrayList<OrdersDTO> getAllOrdersToday() throws Exception {
        ArrayList<OrdersDTO> allOrders = new ArrayList<>();
        ArrayList<Orders> all = dao.getAllToday();
        for (Orders c : all) {
            OrdersDTO orders = new OrdersDTO(c.getId(),
                    c.getCid(),
                    c.getDate(),
                    c.getTime(),
                    c.getAmount(),
                    c.getDiscount()
            );
            orders.setCustomerName(customerDAO.search(c.getCid()).getName());
            orders.setCustomerContact(customerDAO.search(c.getCid()).getContact());

            int count = 0;
            ArrayList<OrderDetail> arrayList = orderDetailDAO.searchAll(c.getId());
            for (OrderDetail od :
                    arrayList) {
                count++;

            }
            orders.setQty(count);
            allOrders.add(orders);
        }
        return allOrders;
    }

    @Override
    public int getLastOrderId() throws Exception {
        Orders lastOrder = dao.getLastOrder();
        return lastOrder.getId();
    }
}
