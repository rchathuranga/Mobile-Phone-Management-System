package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.CrudDAO;
import lk.ijse.projectmp.entity.RepairService;

public interface RepairServiceDAO extends CrudDAO<RepairService,Integer> {
    boolean addWithoutPart(RepairService rs) throws Exception;
}
