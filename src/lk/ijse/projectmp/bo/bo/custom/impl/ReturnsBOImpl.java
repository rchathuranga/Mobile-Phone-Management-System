package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.ReturnsBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.CustomerDAO;
import lk.ijse.projectmp.dao.dao.custom.ItemDAO;
import lk.ijse.projectmp.dao.dao.custom.PhoneDAO;
import lk.ijse.projectmp.dao.dao.custom.ReturnsDAO;
import lk.ijse.projectmp.dto.ReturnsDTO;
import lk.ijse.projectmp.entity.Returns;

import java.util.ArrayList;

public class ReturnsBOImpl implements ReturnsBO {

    private ReturnsDAO dao= (ReturnsDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.RETURNS);
    private CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOMER);
    private ItemDAO itemDAO= (ItemDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ITEM);
    private PhoneDAO phoneDAO= (PhoneDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PHONE);

    @Override
    public boolean addReturns(ReturnsDTO r) throws Exception {
        return dao.add(new Returns(r.getCid(), r.getItemid(), r.getPhoneid(), r.getReason(), r.getPrice()));
    }

    @Override
    public ArrayList<ReturnsDTO> getAllReturns() throws Exception {
        ArrayList<ReturnsDTO> arrayList=new ArrayList<>();
        ArrayList<Returns> all=dao.getAll();
        for (Returns r :all) {
            ReturnsDTO returnsDTO=new ReturnsDTO(r.getId(),r.getCid(),r.getReason(),r.getPrice());
            if(r.getItemid()!=0){
                returnsDTO.setItemid(r.getItemid());
                returnsDTO.setItemName(itemDAO.search(r.getItemid()).getName());
            }else {
                returnsDTO.setItemid(r.getPhoneid());
                returnsDTO.setItemName(phoneDAO.search(r.getPhoneid()).getName());
            }
            returnsDTO.setCustomerName(customerDAO.search(r.getCid()).getName());
            arrayList.add(returnsDTO);
        }
        return arrayList;
    }
}
