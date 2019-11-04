package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.OrderDetailDTO;

import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    ArrayList<OrderDetailDTO> getAll() throws Exception;
    ArrayList<OrderDetailDTO> getAll(int orderId) throws Exception;
    ArrayList<OrderDetailDTO> getAllToday() throws Exception;

}
