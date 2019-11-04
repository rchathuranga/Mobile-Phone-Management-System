package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.RepairServiceBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.RepairPartDAO;
import lk.ijse.projectmp.dao.dao.custom.RepairServiceDAO;
import lk.ijse.projectmp.dto.RepairPartDTO;
import lk.ijse.projectmp.dto.RepairServiceDTO;
import lk.ijse.projectmp.dto.RepairDetailDTO;
import lk.ijse.projectmp.entity.RepairPart;
import lk.ijse.projectmp.entity.RepairService;

import java.util.ArrayList;

public class RepairServiceBOImpl implements RepairServiceBO {

    private RepairServiceDAO dao= (RepairServiceDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.REPAIRSERVICE);
    private RepairPartDAO repairPartDAO= (RepairPartDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.REPAIRPART);

    @Override
    public ArrayList<RepairServiceDTO> getAllService() throws Exception {
        ArrayList<RepairService> all=dao.getAll();
        ArrayList<RepairServiceDTO> arrayList=new ArrayList<>();

        for (RepairService rs :all) {
            arrayList.add(new RepairServiceDTO(
                    rs.getRepairServiceId(),rs.getRepairPartId(),rs.getName(),rs.getPrice()
            ));
        }
        return arrayList;
    }

    @Override
    public RepairDetailDTO searchService(int id) throws Exception {
        RepairService search = dao.search(id);
        RepairDetailDTO rService = new RepairDetailDTO(search.getRepairServiceId(), search.getName(), search.getPrice(), search.getRepairPartId());
        RepairPart part = repairPartDAO.search(search.getRepairPartId());

        if(search.getRepairPartId()!=0) {
            rService.setPartName(part.getPartName());
            rService.setPartPrice(part.getSellingPrice());
            rService.setQty(part.getQty());
        }

        return rService;
    }

    @Override
    public ArrayList<RepairPartDTO> getAllPart() throws Exception {
        ArrayList<RepairPart> all = repairPartDAO.getAll();
        ArrayList<RepairPartDTO> arrayList=new ArrayList<>();

        for (RepairPart rp :all) {
            arrayList.add(new RepairPartDTO(
                    rp.getPartid(),
                    rp.getPartName(),
                    rp.getBuyingPrice(),
                    rp.getSellingPrice(),
                    rp.getQty()
            ));
        }
        return arrayList;
    }

    @Override
    public boolean addService(RepairServiceDTO service) throws Exception {
        if(service.getPart()!=null) {
            RepairPartDTO part = service.getPart();
            boolean addPart = repairPartDAO.add(new RepairPart(part.getName(), part.getBuyingPrice(), part.getSellingPrice(), part.getQty()));
            ArrayList<RepairPart> all = repairPartDAO.getAll();
            int partId = all.get(all.size() - 1).getPartid();
            if (addPart) {
                return dao.add(new RepairService(partId, service.getName(), service.getPrice()));
            }
            return false;
        }else {
            return dao.addWithoutPart(new RepairService(service.getRpid(), service.getName(), service.getPrice()));
        }
    }
}
