package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.PhoneDAO;
import lk.ijse.projectmp.entity.Phone;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PhoneDAOImpl implements PhoneDAO {
    String sql;

    @Override
    public boolean add(Phone p) throws Exception {
        sql="insert into phone(brandid,name,ram,storage,network,qty,warranty,sellingPrice,buyingPrice) VALUES(?,?,?,?,?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,p.getBrandId(),p.getName(),
                p.getRam(),p.getStorage(),p.getNetwork(),p.getQty(),
                p.getWarranty(),p.getSellingPrice(),p.getBuyingPrice());
    }

    @Override
    public boolean delete(Object o) throws Exception {
        sql="DELETE FROM phone WHERE id=?";
        return CrudUtil.executeUpdate(sql,o);
    }

    @Override
    public boolean update(Phone p) throws Exception {
        sql="UPDATE phone SET brandid=?,name=?,ram=?,storage=?,network=?,qty=?,warranty=?,sellingPrice=?,buyingPrice=? WHERE id=?";
        return CrudUtil.executeUpdate(sql,p.getBrandId(),
                p.getName(),p.getRam(),p.getStorage(),
                p.getNetwork(),p.getQty(),p.getWarranty(),
                p.getSellingPrice(),p.getBuyingPrice(),p.getId());
    }

    @Override
    public Phone search(Object o) throws Exception {
        sql="SELECT * FROM phone WHERE id=?";
        Phone phone=null;
        ResultSet rst = CrudUtil.executeQuery(sql, o);
        while (rst.next()){
            phone=new Phone(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getInt(8),
                    rst.getDouble(9),
                    rst.getDouble(10)
            );
        }
        return phone;
    }

    @Override
    public ArrayList<Phone> getAll() throws Exception {
        sql="SELECT * FROM phone";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Phone> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Phone(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getInt(8),
                    rst.getDouble(9),
                    rst.getDouble(10)
            ));
        }
        return arrayList;
    }

    @Override
    public int getTotPhoneQty() throws Exception {
        sql="select sum(qty) from phone";
        ResultSet rst = CrudUtil.executeQuery(sql);
        int i=0;
        while (rst.next()){
            i=rst.getInt(1);
        }
        return i;
    }

    @Override
    public boolean updateQty(Phone p) throws Exception {
        sql="UPDATE phone SET qty=qty-? WHERE id=?";
        return CrudUtil.executeUpdate(sql, p.getQty(), p.getId());
    }
}
