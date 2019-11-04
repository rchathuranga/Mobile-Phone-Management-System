package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.CustomDAO;
import lk.ijse.projectmp.entity.Custom;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomDAOImpl implements CustomDAO {

    String sql;
    @Override
    public ArrayList<Custom> getOrdersofCustomer(int id) throws Exception {
        sql="SELECT c.id AS customerid,o.id AS orderid,o.total,o.date FROM customer c,orders o WHERE c.id=o.cid && c.id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        ArrayList<Custom> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Custom(rst.getInt(1),rst.getInt(2),rst.getDouble(3),rst.getString(4)));
        }
        return arrayList;
    }

    @Override
    public ArrayList<Custom> getCustomerInteract() throws Exception {
        sql="SELECT * FROM customer c,orders o WHERE c.id=o.cid && o.date=curdate() GROUP BY c.id";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Custom> arrayList=new ArrayList<>();

        while (rst.next()){
            Custom c=new Custom();
            c.setcName(rst.getString("name"));
            c.setOrderId(rst.getInt("id"));
            c.setOrderAmount(rst.getDouble("amount"));
            arrayList.add(c);
        }
        return arrayList;
    }

    @Override
    public ArrayList<Custom> getMostSelling() throws Exception {
        sql="SELECT od.itemid,COUNT(od.itemid) AS count,i.name,i.qty,i.sellingPrice FROM orderDetail od,orders o,item i WHERE od.orderId=o.id AND i.id=od.itemid GROUP BY itemid ORDER BY COUNT DESC";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Custom> arrayList=new ArrayList<>();
        while (rst.next()){
                arrayList.add(new Custom(rst.getInt(1),rst.getString(3),rst.getInt(4),rst.getDouble(5)));
        }
        return arrayList;
    }

    @Override
    public ArrayList<Custom> getRepairDetail() throws Exception {
        sql="select r.repid,c.id,c.name,r.phone,s.name,sum(rd.amount),rd.completed from customer c,repair r,repairDetails rd,repair_service s where c.id=r.cid && r.repid=rd.repid && rd.rsid=s.rsid";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Custom> arrayList=new ArrayList<>();

        return arrayList;
    }

    @Override
    public ArrayList<Custom> getRepairsofCustomer(int id) throws Exception {
        sql="SELECT c.id AS customerid,r.repid AS repairid,r.amount,r.date  FROM customer c,repair r WHERE c.id=r.cid && id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        ArrayList<Custom> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Custom(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getDouble(3),
                    rst.getString(4)
            ));
        }
        return arrayList;
    }

    @Override
    public ArrayList<Custom> getLeastSelling() throws Exception {
        sql=" SELECT od.itemid,COUNT(od.itemid) AS count,i.name,i.qty,i.sellingPrice FROM orderdetail od,orders o,item i WHERE od.orderId=o.id AND i.id=od.itemid GROUP BY i.id ORDER BY COUNT asc";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Custom> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Custom(
                    rst.getInt(1),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)
            ));
        }
        return arrayList;
    }

    @Override
    public double getOrderIncome() throws Exception{
        sql="select sum(total) from orders";
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        double oi=0.0;
        if(resultSet.next()){
            oi=resultSet.getDouble(1);
        }
        return oi;
    }

    @Override
    public double getRepairIncome()  throws Exception{
        sql="select sum(amount) from repair";
        ResultSet rst = CrudUtil.executeQuery(sql);
        double ri=0.0;
        if (rst.next()){
            ri=rst.getInt(1);
        }
        return ri;
    }

    @Override
    public double getPhoneExpense()  throws Exception{
        sql="select sum(buyingPrice) from phone";
        ResultSet rst = CrudUtil.executeQuery(sql);
        double pe=0.0;
        if (rst.next()){
            pe=rst.getDouble(1);
        }
        return pe;
    }

    @Override
    public double getItemExpense()  throws Exception{
        sql="select sum(buyingPrice) from item";
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        double ie=0.0;
        if (resultSet.next()) {
            ie = resultSet.getDouble(1);
        }
        return ie;
    }

    @Override
    public double getRepairPartExpence()  throws Exception{
        sql="select sum(buyingPrice) from repair_part";
        ResultSet rst = CrudUtil.executeQuery(sql);
        double pe=0.0;
        if (rst.next()){
            pe=rst.getDouble(1);
        }
        return pe;
    }


}
