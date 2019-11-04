package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.CatagoryBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.CatagoryDAO;
import lk.ijse.projectmp.dto.CatagoryDTO;
import lk.ijse.projectmp.entity.Catagory;

import java.util.ArrayList;

public class CatagoryBOImpl implements CatagoryBO {

    CatagoryDAO dao= (CatagoryDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CATAGORY);

    @Override
    public boolean addCatagory(CatagoryDTO c) throws Exception {
        return dao.add(new Catagory(c.getName()));
    }

    @Override
    public boolean updateCatagory(CatagoryDTO c) throws Exception {
        return dao.update(new Catagory(c.getId(),c.getName()));
    }

    @Override
    public boolean deleteCatagory(int s) throws Exception {
        return dao.delete(s);
    }

    @Override
    public CatagoryDTO searchCatagory(int s) throws Exception {
        Catagory c=dao.search(s);
        return new CatagoryDTO(c.getId(),c.getName());
    }

    @Override
    public ArrayList<CatagoryDTO> getAllCatagory() throws Exception {
        ArrayList<CatagoryDTO> arrayList=new ArrayList<>();
        ArrayList<Catagory> all=dao.getAll();

        for (Catagory c: all){
            arrayList.add(new CatagoryDTO(c.getId(),c.getName()));
        }
        return arrayList;
    }
}
