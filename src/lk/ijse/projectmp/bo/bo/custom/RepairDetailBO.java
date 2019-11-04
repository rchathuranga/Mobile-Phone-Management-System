package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.RepairDetailDTO;

import java.util.ArrayList;

public interface RepairDetailBO extends SuperBO {
    ArrayList<RepairDetailDTO> getAllRepairDetail() throws Exception;
    ArrayList<RepairDetailDTO> getAllDetailOf(int id) throws Exception;
    boolean setCompleted(int id) throws Exception;
    boolean deleteRepairDetail(int i) throws Exception;
    ArrayList<RepairDetailDTO> getUnfinishRepair() throws Exception;
}
