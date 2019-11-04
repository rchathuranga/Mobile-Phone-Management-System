package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.PhoneDTO;

import java.util.ArrayList;

public interface PhoneBO extends SuperBO {
    boolean addPhone(PhoneDTO p) throws Exception;
    boolean updatePhone(PhoneDTO p) throws Exception;
    boolean deletePhone(int id) throws Exception;
    PhoneDTO searchPhone(int id) throws Exception;
    ArrayList<PhoneDTO> getAllPhone() throws Exception;
    int getTotPhoneQty() throws Exception;
}
