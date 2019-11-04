package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.CrudDAO;
import lk.ijse.projectmp.entity.Phone;

public interface PhoneDAO extends CrudDAO<Phone,Object> {
    int getTotPhoneQty() throws Exception;
    boolean updateQty(Phone p) throws Exception;
}
