package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.CustomerDAO;
import lk.ijse.projectmp.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    String sql;

    @Override
    public boolean add(Customer customer) throws Exception {
        sql="INSERT INTO customer(date,name,contact,nic) VALUES(curdate(),?,?,?)";
        return CrudUtil.executeUpdate(sql,customer.getName(), customer.getContact(), customer.getNIC());
    }

    @Override
    public boolean delete(Integer s) throws Exception {
        sql="DELETE FROM customer WHERE id=?";
        return CrudUtil.executeUpdate(sql,s);
    }

    @Override
    public boolean update(Customer c) throws Exception {
        sql="UPDATE customer SET name=?,contact=?,nic=? WHERE id=?";
        return CrudUtil.executeUpdate(sql, c.getName(),c.getContact(),c.getNIC(),c.getId());
    }

    @Override
    public Customer search(Integer o) throws Exception {
        sql="SELECT * FROM customer WHERE id=?";
        ResultSet rst = CrudUtil.executeQuery(sql, o);
        Customer cus=null;
        while (rst.next()){
            cus=new Customer(rst.getInt("id"),rst.getString("name"),rst.getInt("contact"),rst.getString("nic"));
        }
        return cus;
    }

    @Override
    public Customer search(String s) throws Exception {
        sql="SELECT * FROM customer WHERE name=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        Customer cus=null;
        while (rst.next()){
            cus=new Customer(rst.getInt("id"),rst.getString("name"),rst.getInt("contact"),rst.getString("nic"));
        }
        return cus;
    }

    @Override
    public Customer searchByContact(int contact) throws Exception {
        sql="SELECT * FROM customer WHERE contact=?";
        ResultSet rst = CrudUtil.executeQuery(sql, contact);
        Customer c=null;
        while (rst.next()){
            c=new Customer(rst.getString(1),rst.getInt(2),rst.getString(3),rst.getInt(4),rst.getString(5));
        }
        return c;
    }

    @Override
    public ArrayList<Customer> getAll() throws Exception {
        sql="SELECT * FROM customer";
        return executeGetAll(sql);
    }

    @Override
    public ArrayList<Customer> getAll(String date) throws Exception {
        sql="SELECT * FROM customer WHERE date=?";

        return executeGetAll(sql,date);
    }


    public ArrayList<Customer> executeGetAll(String sql,String...date) throws SQLException, ClassNotFoundException {
    ResultSet rst = CrudUtil.executeQuery(sql,date);
    ArrayList<Customer> arrayList = new ArrayList<>();
    while (rst.next()) {
        arrayList.add(new Customer(rst.getInt("id"),
                rst.getString("name"),
                rst.getInt("contact"),
                rst.getString("nic"))
        );
    }
    return arrayList;
    }
}