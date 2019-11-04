package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.ItemBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.ItemDAO;
import lk.ijse.projectmp.dto.ItemDTO;
import lk.ijse.projectmp.entity.Item;

import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO dao= (ItemDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addItem(ItemDTO i) throws Exception {
        return dao.add(new Item(i.getBrandid(),i.getCataid(),i.getName(),i.getWarranty(),i.getQty(),i.getBuyingPrice(),i.getSellingPrice()));
    }

    @Override
    public boolean updateItem(ItemDTO i) throws Exception {
        return dao.update(new Item(
                i.getId(),i.getBrandid(),
                i.getCataid(),i.getName(),
                i.getWarranty(),i.getQty(),
                i.getBuyingPrice(),i.getSellingPrice()
        ));
    }

    @Override
    public boolean deleteItem(int i) throws Exception {
        return dao.delete(i);
    }

    @Override
    public ItemDTO searchItem(int id) throws Exception {
        Item s = dao.search(id);
        return new ItemDTO(s.getId(),s.getBrandid(),s.getCataid(),s.getName(),s.getWarranty(),s.getQty(),s.getBuyingPrice(),s.getSellingPrice());
    }

    @Override
    public ArrayList<ItemDTO> getAllItem() throws Exception {
        ArrayList<ItemDTO> arrayList=new ArrayList<>();
        ArrayList<Item> all=dao.getAll();
        for (Item i :
                all) {
            arrayList.add(new ItemDTO(i.getId(),i.getBrandid(),i.getCataid(),i.getName(),i.getWarranty(),i.getQty(),i.getBuyingPrice(),i.getSellingPrice()));
        }
        return arrayList;
    }

    @Override
    public ArrayList<ItemDTO> getLeastItem() throws Exception {
        ArrayList<ItemDTO> arrayList=new ArrayList<>();
        ArrayList<Item> items=dao.getLeast();
        for (Item i :
                items) {
            arrayList.add(new ItemDTO(i.getId(), i.getName(), i.getQty()));
        }
        return arrayList;
    }

    @Override
    public int getTotItemQty() throws Exception {
        return dao.getTotItemQty();
    }
}
