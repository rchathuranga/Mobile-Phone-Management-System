package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.SDiscountBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.BrandDAO;
import lk.ijse.projectmp.dao.dao.custom.CatagoryDAO;
import lk.ijse.projectmp.dao.dao.custom.SDiscountDAO;
import lk.ijse.projectmp.dto.SDiscountDTO;
import lk.ijse.projectmp.entity.SDiscount;

import java.util.ArrayList;

public class SDiscountBOImpl implements SDiscountBO {

    private CatagoryDAO cataDAO= (CatagoryDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CATAGORY);
    private BrandDAO brandDAO= (BrandDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BRAND);
    private SDiscountDAO dao= (SDiscountDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.SDISCOUNT);

    @Override
    public boolean addSDisount(SDiscountDTO d) throws Exception {
        return dao.add(new SDiscount(d.getStartDate(),d.getEndDate(),0,d.getCata(),d.getBrand(),d.getAmount()));
    }

    @Override
    public ArrayList<SDiscountDTO> getAllSDiscount() throws Exception {
        ArrayList<SDiscountDTO> arrayList=new ArrayList<>();
        ArrayList<SDiscount> all=dao.getAll();

        for (SDiscount sd :all) {
            SDiscountDTO discount=new SDiscountDTO(sd.getId(),
                    sd.getStartDate(),
                    sd.getEndDate(),
                    sd.getDays(),
                    sd.getCataId(),
                    sd.getBrandId(),
                    sd.getAmount()
            );
            discount.setBrandName(brandDAO.search(sd.getBrandId()).getName());
            discount.setCatagoryName(cataDAO.search(sd.getCataId()).getName());
            if(sd.getAmount()<1) {
                discount.setPercentage((sd.getAmount()*100)+"%");
            }else {
                discount.setPercentage(String.valueOf(sd.getAmount()));
            }
            arrayList.add(discount);
        }

        return arrayList;
    }
}
