package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.CrudDAO;
import lk.ijse.projectmp.entity.Item;

import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,Object> {
    ArrayList<Item> getLeast() throws Exception;
    int getTotItemQty() throws Exception;
    boolean updateItemQty(Item i) throws Exception;
}
