package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.ItemDAO;
import lk.ijse.projectmp.entity.Item;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    String sql;

    @Override
    public boolean add(Item item) throws Exception {
        sql="INSERT INTO item(brandid,cataid,name,warranty,qty,buyingPrice,sellingPrice) VALUES(?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql, item.getBrandid(),item.getCataid(),item.getName(),item.getWarranty(),item.getQty(),item.getBuyingPrice(),item.getSellingPrice());
    }

    @Override
    public boolean delete(Object o) throws Exception {
        sql="DELETE FROM item WHERE id=?";
        return CrudUtil.executeUpdate(sql,o);
    }

    @Override
    public boolean update(Item item) throws Exception {
        sql="UPDATE item SET brandid=?,cataid=?,name=?,warranty=?,qty=?,buyingPrice=?,sellingPrice=? WHERE id=?";
        return CrudUtil.executeUpdate(sql,item.getBrandid(),item.getCataid(),item.getName(),item.getWarranty(),item.getQty(),item.getBuyingPrice(),item.getSellingPrice(),item.getId());
    }

    @Override
    public Item search(Object o) throws Exception {
        sql="Select * from item where id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, o);
        Item item=null;
        while (rst.next()){
            item=new Item(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getString(4),
                    rst.getInt(5),rst.getInt(6),rst.getDouble(7),rst.getDouble(8));
        }
        return item;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        sql="SELECT * FROM item";
        ArrayList<Item> arrayList=new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql);
        while(rst.next()){
            Item item=new Item(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getString(4),
                    rst.getInt(5),rst.getInt(6),rst.getDouble(7),rst.getDouble(8));
             arrayList.add(item);
        }
        return arrayList;
    }

    @Override
    public ArrayList<Item> getLeast() throws Exception {
        sql="SELECT id,name,qty FROM item WHERE qty<10";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Item> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Item(rst.getInt("id"),rst.getString("name"),rst.getInt("qty")));
        }
        return arrayList;
    }

    @Override
    public int getTotItemQty() throws Exception {
        sql="select sum(qty) from item";
        ResultSet rst = CrudUtil.executeQuery(sql);
        int i=0;
        while (rst.next()){
            i=rst.getInt(1);
        }
        return i;
    }

    @Override
    public boolean updateItemQty(Item i) throws Exception {
        sql="UPDATE item SET qty=qty-? WHERE id=?";
        return CrudUtil.executeUpdate(sql,i.getQty(),i.getId());
    }
}
