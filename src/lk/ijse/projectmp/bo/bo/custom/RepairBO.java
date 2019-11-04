package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.RepairDTO;

import java.util.ArrayList;

public interface RepairBO extends SuperBO {
    boolean addRepair(RepairDTO r) throws Exception;
    boolean deleteRepair(int id) throws Exception;
    boolean updateRepair(RepairDTO r) throws Exception;
    RepairDTO searchRepair(int i) throws Exception;
    ArrayList<RepairDTO> getAllRepair() throws Exception;
    int repairToDo() throws Exception;
    boolean finish(int i) throws Exception;
}
