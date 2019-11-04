package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.CrudDAO;
import lk.ijse.projectmp.entity.Repair;

public interface RepairDAO extends CrudDAO<Repair,Integer> {
    int repairToDo() throws Exception;
    boolean finish(int i) throws Exception;
    Repair getLastRepair() throws Exception;
}
