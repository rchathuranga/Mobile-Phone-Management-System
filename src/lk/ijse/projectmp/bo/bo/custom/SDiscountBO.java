package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.SDiscountDTO;

import java.util.ArrayList;

public interface SDiscountBO extends SuperBO {
    boolean addSDisount(SDiscountDTO discountDTO) throws Exception;
    ArrayList<SDiscountDTO> getAllSDiscount() throws Exception;
}
