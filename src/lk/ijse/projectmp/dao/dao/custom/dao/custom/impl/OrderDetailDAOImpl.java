package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.OrderDetailDAO;
import lk.ijse.projectmp.entity.OrderDetail;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    String sql;
    @Override
    public boolean add(OrderDetail od) throws Exception {
       if(od.getItemid()==0){
           sql="INSERT INTO orderdetail(orderid,phoneid,qty,amount,discount,total,imei_no) VALUES(?,?,?,?,?,?,?)";
           return CrudUtil.executeUpdate(sql,od.getOrderid(),od.getPhoneid(),od.getQty(),od.getAmount(),od.getDiscount(),od.getTotal(),od.getImei());
       }else {
           sql="INSERT INTO orderdetail(orderid,itemid,qty,amount,discount,total) VALUES(?,?,?,?,?,?)";
           return CrudUtil.executeUpdate(sql,od.getOrderid(),od.getItemid(),od.getQty(),od.getAmount(),od.getDiscount(),od.getTotal());
       }
    }

    @Override
    public boolean delete(Integer integer)  {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail)  {
        return false;
    }

    @Override
    public OrderDetail search(Integer integer)  {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws Exception {
        sql="SELECT * FROM ORDERDETAIL";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<OrderDetail> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new OrderDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6),
                    rst.getDouble(7),
                    rst.getDouble(8),
                    rst.getDouble(9),
                    rst.getInt(10)
            ));
        }
        return arrayList;
    }

    @Override
    public ArrayList<OrderDetail> searchAll(int orderId) throws Exception {
        sql="SELECT * FROM orderdetail WHERE orderid=?";
        ResultSet rst = CrudUtil.executeQuery(sql, orderId);
        ArrayList<OrderDetail> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new OrderDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(7),
                    rst.getDouble(6),
                    rst.getDouble(8),
                    rst.getDouble(9),
                    rst.getInt(10)
            ));
        }
        return arrayList;
    }
}
