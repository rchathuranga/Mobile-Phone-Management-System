package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.RepairPartDTO;
import lk.ijse.projectmp.dto.RepairServiceDTO;
import lk.ijse.projectmp.dto.RepairDetailDTO;

import java.util.ArrayList;

public interface RepairServiceBO extends SuperBO {
    ArrayList<RepairServiceDTO> getAllService() throws Exception;
    RepairDetailDTO searchService(int id) throws Exception;
    ArrayList<RepairPartDTO> getAllPart() throws Exception;
    boolean addService(RepairServiceDTO service) throws Exception;
}
