package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.CrudDAO;
import lk.ijse.projectmp.entity.Orders;

import java.util.ArrayList;

public interface OrdersDAO extends CrudDAO<Orders,Object> {
    ArrayList<Orders> getAllToday() throws Exception;
    Orders getLastOrder() throws Exception;
}
