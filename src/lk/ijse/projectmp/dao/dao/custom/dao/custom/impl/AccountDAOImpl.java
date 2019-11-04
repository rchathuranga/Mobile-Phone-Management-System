package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.AccountDAO;
import lk.ijse.projectmp.entity.Account;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountDAOImpl implements AccountDAO {
    String sql;

    @Override
    public boolean add(Account a) throws Exception {
        sql="INSERT INTO account(name,username,password) VALUES(?,?,?)";
        return CrudUtil.executeUpdate(sql,a.getName(),a.getUsername(),a.getPassword());
    }

    @Override
    public boolean delete(Object s) {
        return false;
    }

    @Override
    public boolean update(Account a) throws Exception {
        sql="UPDATE account SET password=? WHERE name=? && username=?";
        return CrudUtil.executeUpdate(sql,a.getPassword(),a.getName(),a.getUsername());
    }

    @Override
    public Account search(Object s) throws Exception {
        sql="SELECT * FROM account WHERE username=?";
        ResultSet rst = CrudUtil.executeQuery(sql, s);
        Account account=null;
        while (rst.next()){
            account=new Account(rst.getInt(1),rst.getString("name"),rst.getString("username"),rst.getString("password"));
        }
        return account;
    }

    @Override
    public ArrayList<Account> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM account");
        ArrayList<Account> arrayList=new ArrayList<>();
        while(rst.next()){
            arrayList.add(new Account(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return arrayList;
    }
}
