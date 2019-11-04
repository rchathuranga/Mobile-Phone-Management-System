package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.CrudDAO;
import lk.ijse.projectmp.entity.RepairDetail;

import java.util.ArrayList;

public interface RepairDetailDAO extends CrudDAO<RepairDetail,Integer> {
    ArrayList<RepairDetail> getRepairdetailOf(int i) throws Exception;
    boolean setCompleted(int i) throws Exception;
    ArrayList<RepairDetail> getUnfinishRepair() throws Exception;
}
