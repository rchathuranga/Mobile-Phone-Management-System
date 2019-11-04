package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.OrdersDTO;
import lk.ijse.projectmp.entity.Orders;

import java.util.ArrayList;

public interface OrdersBO extends SuperBO {
    boolean addOrders(OrdersDTO o) throws Exception;
    OrdersDTO searchOrders(int id) throws Exception;
    ArrayList<OrdersDTO> getAllOrders() throws Exception;
    ArrayList<OrdersDTO> getAllOrdersToday() throws Exception;
    int getLastOrderId() throws Exception;
}
