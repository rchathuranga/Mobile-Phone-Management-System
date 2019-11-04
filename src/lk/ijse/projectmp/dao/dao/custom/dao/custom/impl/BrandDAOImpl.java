package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.BrandDAO;
import lk.ijse.projectmp.entity.Brand;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BrandDAOImpl implements BrandDAO {

    String sql;
    @Override
    public boolean add(Brand brand) throws Exception {
        sql="INSERT INTO brand(name) VALUES(?)";
        return CrudUtil.executeUpdate(sql,brand.getName());
    }

    @Override
    public boolean delete(Integer s) throws Exception {
        sql="DELETE FROM brand WHERE id=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Brand brand) throws Exception {
        sql="UPDATE brand SET name=? WHERE id=?";
        return CrudUtil.executeUpdate(sql,brand.getName(),brand.getId());
    }

    @Override
    public Brand search(Integer s) throws Exception {
        sql="SELECT * FROM brand WHERE id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);

        Brand brand=null;
        while (rst.next()){
            brand=new Brand(rst.getInt(1),rst.getString(2));
        }
        return brand;
    }

    @Override
    public ArrayList<Brand> getAll() throws Exception {
        sql="SELECT * FROM brand";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Brand> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Brand(rst.getInt("id"),rst.getString("name")));
        }
        return arrayList;
    }
}
