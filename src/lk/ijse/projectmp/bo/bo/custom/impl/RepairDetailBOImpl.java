package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.RepairDetailBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.*;
import lk.ijse.projectmp.dao.dao.custom.dao.custom.impl.RepairPartDAOImpl;
import lk.ijse.projectmp.dao.dao.custom.dao.custom.impl.RepairServiceDAOImpl;
import lk.ijse.projectmp.dto.RepairDetailDTO;
import lk.ijse.projectmp.entity.Repair;
import lk.ijse.projectmp.entity.RepairDetail;
import lk.ijse.projectmp.entity.RepairService;

import java.util.ArrayList;

public class RepairDetailBOImpl implements RepairDetailBO {

    private RepairDetailDAO dao= (RepairDetailDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.REPAIRDETAIL);
    private RepairDAO repairDAO= (RepairDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.REPAIR);
    private  CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOMER);
    private RepairServiceDAO repairServiceDAO= (RepairServiceDAOImpl) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.REPAIRSERVICE);
    private  RepairPartDAO repairPartDAO= (RepairPartDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.REPAIRPART);

    @Override
    public ArrayList<RepairDetailDTO> getAllRepairDetail() throws Exception {
        ArrayList<RepairDetailDTO> arrayList=new ArrayList<>();
        ArrayList<RepairDetail> all=dao.getAll();
        for (RepairDetail rd :all) {
            RepairDetailDTO rdDTO=new RepairDetailDTO(
                    rd.getId(),
                    rd.getRepairId(),
                    rd.getRepairServiceId(),
                    rd.getAmount(),
                    rd.getCompleted()
            );
            rdDTO.setCustomerName(customerDAO.search(repairDAO.search(rd.getRepairId()).getCid()).getName());
            rdDTO.setCustomerContact(customerDAO.search(repairDAO.search(rd.getRepairId()).getCid()).getContact());
            rdDTO.setPhone(repairDAO.search(rd.getRepairId()).getPhone());
            arrayList.add(rdDTO);
        }
        return arrayList;
    }

    @Override
    public ArrayList<RepairDetailDTO> getAllDetailOf(int id) throws Exception {
        ArrayList<RepairDetail> repairdetailOf = dao.getRepairdetailOf(id);
        ArrayList<RepairDetailDTO> arrayList=new ArrayList<>();

        for (RepairDetail rd :repairdetailOf) {

            RepairDetailDTO rdDTO=new RepairDetailDTO(
                    rd.getId(),
                    rd.getRepairId(),
                    rd.getRepairServiceId(),
                    rd.getAmount(),
                    rd.getCompleted()
            );
            rdDTO.setCustomerName(customerDAO.search(repairDAO.search(rd.getRepairId()).getCid()).getName());
            rdDTO.setCustomerContact(customerDAO.search(repairDAO.search(rd.getRepairId()).getCid()).getContact());
            rdDTO.setPhone(repairDAO.search(rd.getRepairId()).getPhone());
            rdDTO.setServiceName(repairServiceDAO.search(rdDTO.getRepairServiceId()).getName());
            rdDTO.setServicePrice(repairServiceDAO.search(rdDTO.getRepairServiceId()).getPrice());
            rdDTO.setPartId(repairServiceDAO.search(rdDTO.getRepairServiceId()).getRepairPartId());
            if(rdDTO.getPartId()!=0){
                rdDTO.setPartName(repairPartDAO.search(rdDTO.getPartId()).getPartName());
                rdDTO.setPartPrice(repairPartDAO.search(rdDTO.getPartId()).getSellingPrice());
            }
            arrayList.add(rdDTO);
        }
        return arrayList;
    }

    @Override
    public boolean setCompleted(int id) throws Exception {
        return dao.setCompleted(id);
    }

    @Override
    public boolean deleteRepairDetail(int i) throws Exception {
        return dao.delete(i);
    }

    @Override
    public ArrayList<RepairDetailDTO> getUnfinishRepair() throws Exception {
        ArrayList<RepairDetailDTO> arrayList=new ArrayList<>();
        ArrayList<RepairDetail> unfinishRepair = dao.getUnfinishRepair();

        for (RepairDetail rd :unfinishRepair) {

            RepairDetailDTO rdDTO = new RepairDetailDTO(
                    rd.getId(),
                    rd.getRepairId(),
                    rd.getRepairServiceId(),
                    rd.getAmount(),
                    rd.getCompleted()
            );
            rdDTO.setServiceName(repairServiceDAO.search(rd.getRepairServiceId()).getName());
            rdDTO.setCustomerName(customerDAO.search(repairDAO.search(rd.getRepairId()).getCid()).getName());
            rdDTO.setCustomerContact(customerDAO.search(repairDAO.search(rd.getRepairId()).getCid()).getContact());
            rdDTO.setPhone(repairDAO.search(rd.getRepairId()).getPhone());
            arrayList.add(rdDTO);
        }
        return arrayList;
    }

}
