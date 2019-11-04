package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.CatagoryDTO;

import java.util.ArrayList;

public interface CatagoryBO extends SuperBO {
    boolean addCatagory(CatagoryDTO c) throws Exception;
    boolean updateCatagory(CatagoryDTO c) throws Exception;
    boolean deleteCatagory(int id) throws Exception;
    CatagoryDTO searchCatagory(int s) throws Exception;
    ArrayList<CatagoryDTO> getAllCatagory() throws Exception;
}
