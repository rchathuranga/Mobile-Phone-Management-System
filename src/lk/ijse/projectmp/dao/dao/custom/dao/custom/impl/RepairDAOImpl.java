package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.RepairDAO;
import lk.ijse.projectmp.entity.Repair;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RepairDAOImpl implements RepairDAO {

    String sql;

    @Override
    public boolean add(Repair repair) throws Exception {
        sql="Insert INTO repair(cid,date,time,amount,phone,descrition) VALUES(?,curdate(),now(),?,?,?)";
        return CrudUtil.executeUpdate(sql,repair.getCid(),repair.getAmount(),repair.getPhone(),repair.getDescription());
    }

    @Override
    public boolean delete(Integer o) throws Exception {
        sql="DELETE FROM repair WHERE repid=?";
        return CrudUtil.executeUpdate(sql,o);
    }

    @Override
    public boolean update(Repair repair) throws Exception {
        sql="UPDATE repair SET descrition=? WHERE repid=?";
        return CrudUtil.executeUpdate(sql,repair.getDescription(),repair.getRepid());
    }

    @Override
    public Repair search(Integer o) throws Exception {
       sql="SELECT * FROM repair WHERE repid=?";
        ResultSet rst = CrudUtil.executeQuery(sql, o);
        Repair repair=null;
        while (rst.next()){
            repair=new Repair(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }
        return repair;
    }

    @Override
    public ArrayList<Repair> getAll() throws Exception {
        sql="SELECT * FROM repair";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<Repair> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new Repair(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return arrayList;
    }

    @Override
    public int repairToDo() throws Exception {
        int count=0;
        sql="select count(id) from repairdetail where completed='no'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        if(rst.next()){
            count=rst.getInt(1);
        }
        return count;
    }

    @Override
    public boolean finish(int i) throws Exception {
        sql="UPDATE repair SET finish='yes' WHERE repid=?";
        return CrudUtil.executeUpdate(sql,i);
    }

    @Override
    public Repair getLastRepair() throws Exception {
        ArrayList<Repair> arrayList=getAll();
        return arrayList.get(arrayList.size()-1);
    }

}
