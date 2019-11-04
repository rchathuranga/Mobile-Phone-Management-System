package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.RepairDetailDAO;
import lk.ijse.projectmp.entity.RepairDetail;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RepairDetailDAOImpl implements RepairDetailDAO {
    String sql;

    @Override
    public boolean add(RepairDetail rd) throws Exception {
       sql="INSERT INTO repairdetail(repid,rsid,amount,completed) VALUES(?,?,?,'no')";
       return CrudUtil.executeUpdate(sql,rd.getRepairId(),rd.getRepairServiceId(),rd.getAmount());
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        sql="DELETE FROM repairDetail WHERE id=?";
        return CrudUtil.executeUpdate(sql,integer);
    }

    @Override
    public boolean update(RepairDetail repairDetail)  {
        return false;
    }

    @Override
    public RepairDetail search(Integer integer) {
        return null;
    }

    @Override
    public ArrayList<RepairDetail> getAll() throws Exception {
        sql="SELECT * FROM REPAIRDETAIL";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<RepairDetail> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new RepairDetail(
                rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getString(5)
            ));
        }
        return arrayList;
    }

    @Override
    public ArrayList<RepairDetail> getRepairdetailOf(int i) throws Exception {
        sql="SELECT * FROM REPAIRDETAIL WHERE repid=?";
        ResultSet rst = CrudUtil.executeQuery(sql,i);
        ArrayList<RepairDetail> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new RepairDetail(
                    rst.getInt(1),
                    rst.getInt(2),
                    rst.getInt(3),
                    rst.getDouble(4),
                    rst.getString(5)
            ));
        }
        return arrayList;
    }

    @Override
    public boolean setCompleted(int i) throws Exception {
        sql="UPDATE repairdetail SET completed='yes' WHERE id=?";
        return CrudUtil.executeUpdate(sql,i);
    }

    @Override
    public ArrayList<RepairDetail> getUnfinishRepair() throws Exception {
        sql="select * from repair r,repairdetail rd WHERE r.repid=rd.repid && r.finish='no'";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<RepairDetail> arrayList=new ArrayList<>();
        while (rst.next()){
            arrayList.add(new RepairDetail(
                    rst.getInt(9),
                    rst.getInt(10),
                    rst.getInt(11),
                    rst.getDouble(12),
                    rst.getString(13)
            ));
        }
        return arrayList;
    }

}
