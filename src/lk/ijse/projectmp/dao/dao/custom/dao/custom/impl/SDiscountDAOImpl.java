package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.SDiscountDAO;
import lk.ijse.projectmp.entity.SDiscount;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SDiscountDAOImpl implements SDiscountDAO {

    String sql;

    @Override
    public boolean add(SDiscount s) throws Exception {
        sql="INSERT INTO seasonal_discount(cataid,brandid,startdate,enddate,amount) VALUES (?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,s.getCataId(),s.getBrandId(),s.getStartDate(),s.getEndDate(),s.getAmount());
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public boolean update(SDiscount sDiscount)  {
        return false;
    }

    @Override
    public SDiscount search(Object o)  {
        return null;
    }

    @Override
    public ArrayList<SDiscount> getAll() throws Exception {
        sql="select *,datediff(enddate,startdate) as days  from seasonal_discount;";
        ArrayList<SDiscount> arrayList=new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql);

        while (rst.next()){
            arrayList.add(new SDiscount(
                    rst.getInt(1),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(7),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getDouble(6)
            ));
        }
        return arrayList;
    }
}
