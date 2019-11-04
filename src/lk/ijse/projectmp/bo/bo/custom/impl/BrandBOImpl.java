package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.BrandBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.BrandDAO;
import lk.ijse.projectmp.dto.BrandDTO;
import lk.ijse.projectmp.entity.Brand;

import java.util.ArrayList;


public class BrandBOImpl implements BrandBO {

    BrandDAO dao= (BrandDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BRAND);

    @Override
    public boolean addBrand(BrandDTO b) throws Exception {
        return dao.add(new Brand(b.getName()));
    }

    @Override
    public boolean updateBrand(BrandDTO b) throws Exception {
        return dao.update(new Brand(b.getId(),b.getName()));
    }

    @Override
    public boolean deleteBrand(int id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public BrandDTO searchBrand(int id) throws Exception {
        Brand search = dao.search(id);
        return new BrandDTO(search.getId(),search.getName());
    }

    @Override
    public ArrayList<BrandDTO> getAllBrand() throws Exception{
        ArrayList<BrandDTO> allBrand= new ArrayList<>();
        ArrayList<Brand> all=dao.getAll();

        for (Brand b:all) {
            allBrand.add(new BrandDTO(b.getId(),b.getName()));
        }
        return allBrand;
    }
}
