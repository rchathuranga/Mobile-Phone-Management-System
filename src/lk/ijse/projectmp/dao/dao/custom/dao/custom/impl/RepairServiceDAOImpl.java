package lk.ijse.projectmp.dao.dao.custom.dao.custom.impl;

import lk.ijse.projectmp.dao.CrudUtil;
import lk.ijse.projectmp.dao.dao.custom.RepairServiceDAO;
import lk.ijse.projectmp.entity.RepairService;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RepairServiceDAOImpl implements RepairServiceDAO {
    String sql;

    @Override
    public boolean add(RepairService rs) throws Exception {
        sql = "INSERT INTO repair_service(rpid,name,price) VALUES(?,?,?)";
        return CrudUtil.executeUpdate(sql, rs.getRepairPartId(), rs.getName(), rs.getPrice());
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(RepairService repairService) throws Exception {
        sql="UPDATE repair_service SET name=?,price=? WHERE rsid=?";
        return CrudUtil.executeUpdate(sql,repairService.getName(),repairService.getPrice(),repairService.getRepairServiceId());
    }

    @Override
    public RepairService search(Integer id) throws Exception {
        sql = "SELECT * FROM repair_service WHERE rsid=?";
        ResultSet rst = CrudUtil.executeQuery(sql, id);
        RepairService repairService = null;
        while (rst.next()) {
            repairService = new RepairService(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
        }
        return repairService;
    }

    @Override
    public ArrayList<RepairService> getAll() throws Exception {
        sql = "SELECT * FROM repair_service";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ArrayList<RepairService> arrayList = new ArrayList<>();

        while (rst.next()) {
            arrayList.add(new RepairService(rst.getInt(1), rst.getInt(2), rst.getString(3), rst.getDouble(4)));
        }
        return arrayList;
    }

    @Override
    public boolean addWithoutPart(RepairService rs) throws Exception {
        sql = "INSERT INTO repair_service(name,price) VALUES(?,?)";
        return CrudUtil.executeUpdate(sql, rs.getName(), rs.getPrice());
    }
}
