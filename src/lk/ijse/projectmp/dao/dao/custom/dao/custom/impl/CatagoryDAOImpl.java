package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.CatagoryDAO;
import lk.ijse.projectmp.entity.Catagory;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CatagoryDAOImpl implements CatagoryDAO {

    String sql;
    @Override
    public boolean add(Catagory c) throws Exception {
        sql="INSERT INTO catagory(name) VALUES(?)";
        return CrudUtil.executeUpdate(sql,c.getName());
    }

    @Override
    public boolean delete(Integer s) throws Exception {
        sql="DELETE FROM catagory WHERE id=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Catagory c) throws Exception {
        sql="UPDATE catagory SET name=? WHERE id=?";
        return CrudUtil.executeUpdate(sql,c.getName(),c.getId());
    }

    @Override
    public Catagory search(Integer s) throws Exception {
        sql="SELECT * FROM catagory WHERE id=?";
        Catagory c=null;
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        while (rst.next()){
            c=new Catagory(rst.getInt("id"),rst.getString("name"));
        }
        return c;
    }

    @Override
    public ArrayList<Catagory> getAll() throws Exception {
        sql="SELECT * FROM catagory";
        ArrayList<Catagory> arrayList=new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery(sql);
        while (rst.next()){
            arrayList.add(new Catagory(rst.getInt("id"),rst.getString("name")));
        }
        return arrayList;
    }
}
