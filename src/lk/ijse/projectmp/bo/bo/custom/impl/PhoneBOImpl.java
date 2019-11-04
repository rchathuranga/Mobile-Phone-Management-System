package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.PhoneBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.PhoneDAO;
import lk.ijse.projectmp.dto.PhoneDTO;
import lk.ijse.projectmp.entity.Phone;

import java.util.ArrayList;

public class PhoneBOImpl implements PhoneBO {

    PhoneDAO dao= (PhoneDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PHONE);

    @Override
    public boolean addPhone(PhoneDTO p) throws Exception {
        Phone phone=new Phone(p.getId(),p.getBrandid(),
                p.getName(),p.getRam(),p.getStorage(),
                p.getNetwork(),p.getQty(),p.getWarranty(),
                p.getSellingPrice(),p.getBuyingPrice()
        );
        return dao.add(phone);
    }

    @Override
    public boolean updatePhone(PhoneDTO p) throws Exception {
        return dao.update(new Phone(p.getId(),p.getBrandid(),
                p.getName(),p.getRam(),p.getStorage(),
                p.getNetwork(),p.getQty(),p.getWarranty()
                ,p.getBuyingPrice(),p.getSellingPrice()
        ));
    }

    @Override
    public boolean deletePhone(int id)  {
        return false;
    }

    @Override
    public PhoneDTO searchPhone(int id) throws Exception {
        Phone search = dao.search(id);
        return new PhoneDTO(search.getId(),
                search.getBrandId(),search.getName(),
                search.getRam(),search.getStorage(),
                search.getNetwork(),search.getQty(),
                search.getWarranty(),search.getSellingPrice(),
                search.getBuyingPrice());
    }

    @Override
    public ArrayList<PhoneDTO> getAllPhone() throws Exception {
        ArrayList<PhoneDTO> arrayList=new ArrayList<>();
        ArrayList<Phone> allPhone=dao.getAll();
        for (Phone p : allPhone) {
            arrayList.add(new PhoneDTO(
                    p.getId(),p.getBrandId(),p.getName(),
                    p.getRam(),p.getStorage(),p.getNetwork(),p.getQty(),
                    p.getWarranty(),p.getSellingPrice(),p.getBuyingPrice()
            ));
        }
        return arrayList;
    }

    @Override
    public int getTotPhoneQty() throws Exception {
        return dao.getTotPhoneQty();
    }
}
