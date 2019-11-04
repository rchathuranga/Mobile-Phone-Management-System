package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.ReturnsDAO;
import lk.ijse.projectmp.entity.Returns;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ReturnsDAOImpl implements ReturnsDAO {
    String sql;
    @Override
    public boolean add(Returns returns) throws Exception {
        if(returns.getItemid()==0){
            sql="INSERT INTO returns(cid,phoneid,reason,price) VALUES(?,?,?,?)";
            return CrudUtil.executeUpdate(sql,returns.getCid(),returns.getPhoneid(),returns.getReason(),returns.getPrice());
        }else {
            sql="INSERT INTO returns(cid,itemid,reason,price) VALUES(?,?,?,?)";
            return CrudUtil.executeUpdate(sql,returns.getCid(),returns.getItemid(),returns.getReason(),returns.getPrice());
        }
    }

    @Override
    public boolean delete(Object o) throws Exception {
        return false;
    }

    @Override
    public boolean update(Returns returns) {
        return false;
    }

    @Override
    public Returns search(Object o)  {
        return null;
    }

    @Override
    public ArrayList<Returns> getAll() throws Exception {
        sql="SELECT * FROM returns";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Returns>arrayList=new ArrayList<>();

        while (rst.next()){
            arrayList.add(new Returns(rst.getInt("id"),
                    rst.getInt("cid"),
                    rst.getInt("itemid"),
                    rst.getInt("phoneid"),
                    rst.getString("reason"),
                    rst.getDouble("price")
            ));
        }
        return arrayList;
    }
}
