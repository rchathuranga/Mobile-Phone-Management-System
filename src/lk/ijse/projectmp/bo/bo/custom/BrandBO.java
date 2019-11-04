package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.BrandDTO;

import java.util.ArrayList;

public interface BrandBO extends SuperBO {
    boolean addBrand(BrandDTO b) throws Exception;
    boolean updateBrand(BrandDTO b) throws Exception;
    boolean deleteBrand(int id) throws Exception;
    BrandDTO searchBrand(int id) throws Exception;
    ArrayList<BrandDTO> getAllBrand() throws Exception;
}
