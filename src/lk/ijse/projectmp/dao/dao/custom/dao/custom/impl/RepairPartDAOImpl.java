package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.RepairPartDAO;
import lk.ijse.projectmp.entity.RepairPart;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RepairPartDAOImpl implements RepairPartDAO {

    String sql;

    @Override
    public boolean add(RepairPart repairPart) throws Exception {
        sql="INSERT INTO repair_part(name,buyingPrice,sellingPrice,qty) VALUES(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,repairPart.getPartName(),repairPart.getBuyingPrice(),repairPart.getSellingPrice(),repairPart.getQty());
    }

    @Override
    public boolean delete(Integer integer)  {
        return false;
    }

    @Override
    public boolean update(RepairPart repairPart) {
        return false;
    }

    @Override
    public RepairPart search(Integer integer) throws Exception {
        sql="SELECT * FROM repair_part WHERE rpid=?";
        ResultSet rst = CrudUtil.executeQuery(sql, integer);
        RepairPart part=null;
        while (rst.next()){
            part=new RepairPart(rst.getInt(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getInt("qty")
            );
        }
        return part;
    }

    @Override
    public ArrayList<RepairPart> getAll() throws Exception {
        sql="SELECT * FROM repair_part";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<RepairPart> arrayList=new ArrayList<>();

        while (rst.next()){
            arrayList.add(new RepairPart(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getInt("qty")
            ));
        }
        return arrayList;
    }
}
