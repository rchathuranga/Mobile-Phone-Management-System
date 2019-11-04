package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.ReturnsDTO;

import java.util.ArrayList;

public interface ReturnsBO extends SuperBO {
    boolean addReturns(ReturnsDTO r) throws Exception;
    ArrayList<ReturnsDTO> getAllReturns() throws Exception;
}
