package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.CrudDAO;
import lk.ijse.projectmp.entity.OrderDetail;

import java.util.ArrayList;

public interface OrderDetailDAO extends CrudDAO<OrderDetail,Integer> {
    ArrayList<OrderDetail> searchAll(int orderId) throws Exception;
}
