package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.OrdersDAO;
import lk.ijse.projectmp.entity.Orders;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrdersDAOImpl implements OrdersDAO {

    String sql;

    @Override
    public boolean add(Orders orders) throws Exception {
        sql="INSERT INTO orders(cid,date,time,amount,discount,total) VALUES(?,curdate(),now(),?,?,?)";
        return CrudUtil.executeUpdate(sql,orders.getCid(),orders.getAmount(),orders.getDiscount(),orders.getTotal());
    }

    @Override
    public boolean delete(Object o)  {
        return false;
    }

    @Override
    public boolean update(Orders orders) {
        return false;
    }

    @Override
    public Orders search(Object o) throws Exception {
       sql="SELECT * FROM orders WHERE id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, o);
        Orders orders=null;
        while (rst.next()){
            orders=new Orders(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getDouble(7)
            );
        }
        return orders;
    }

    @Override
    public ArrayList<Orders> getAll() throws Exception {
        sql="SELECT * FROM orders";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Orders> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Orders(rst.getInt("id"),
                    rst.getInt("cid"),
                    rst.getString("date"),
                    rst.getString("time"),
                    rst.getDouble("amount"),
                    rst.getDouble("discount")
            ));
        }
        return arrayList;
    }

    @Override
    public ArrayList<Orders> getAllToday() throws Exception {
        sql="SELECT * FROM orders WHERE date=curdate()";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Orders> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Orders(rst.getInt("id"),
                    rst.getInt("cid"),
                    rst.getString("date"),
                    rst.getString("time"),
                    rst.getDouble("amount"),
                    rst.getDouble("discount")
            ));
        }
        return arrayList;
    }

    @Override
    public Orders getLastOrder() throws Exception {
        sql="SELECT * FROM orders";
        ResultSet rst= CrudUtil.executeQuery(sql);
        Orders orders=null;
        while (rst.next()){
            orders=new Orders(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getDouble(6)
            );
        }
        return orders;
    }
}
