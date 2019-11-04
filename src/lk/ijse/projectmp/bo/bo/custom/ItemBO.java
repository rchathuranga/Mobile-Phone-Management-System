package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.ItemDTO;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean addItem(ItemDTO i) throws Exception;
    boolean updateItem(ItemDTO i) throws Exception;
    boolean deleteItem(int i) throws Exception;
    ItemDTO searchItem(int id) throws Exception;
    ArrayList<ItemDTO> getAllItem() throws Exception;
    ArrayList<ItemDTO> getLeastItem() throws Exception;
    int getTotItemQty() throws Exception;
}
