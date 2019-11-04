package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.CustomDTO;

import java.util.ArrayList;

public interface CustomBO extends SuperBO {
    ArrayList<CustomDTO> getOrdersofCustomer(int id) throws Exception;
    ArrayList<CustomDTO> getCustomerInteract() throws Exception;
    ArrayList<CustomDTO> getAllItemandPhone() throws Exception;
    ArrayList<CustomDTO> getMostSelling() throws Exception;
    ArrayList<CustomDTO> getRepairDetail() throws Exception;
    ArrayList<CustomDTO> getRepairsofCustomer(int id) throws Exception;
    ArrayList<CustomDTO> getLeastSelling() throws Exception;
    CustomDTO getProfitDetail() throws Exception;
}
