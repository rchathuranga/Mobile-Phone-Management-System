package lk.ijse.projectmp.bo.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.projectmp.bo.bo.custom.RepairBO;
import lk.ijse.projectmp.bo.bo.custom.RepairDetailBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.CustomDAO;
import lk.ijse.projectmp.dao.dao.custom.CustomerDAO;
import lk.ijse.projectmp.dao.dao.custom.RepairDAO;
import lk.ijse.projectmp.dao.dao.custom.RepairDetailDAO;
import lk.ijse.projectmp.dao.dao.custom.dao.custom.impl.RepairDAOImpl;
import lk.ijse.projectmp.db.DBConnection;
import lk.ijse.projectmp.dto.RepairDTO;
import lk.ijse.projectmp.dto.RepairDetailDTO;
import lk.ijse.projectmp.entity.Repair;
import lk.ijse.projectmp.entity.RepairDetail;

import java.sql.Connection;
import java.util.ArrayList;

public class RepairBOImpl implements RepairBO {
    private RepairDAO dao= (RepairDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.REPAIR);
    private CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOMER);
    private RepairDetailDAO repairDetailDAO= (RepairDetailDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.REPAIRDETAIL);

    @Override
    public boolean addRepair(RepairDTO r) throws Exception {
        Connection con= DBConnection.getInstance().getConnection();
        try {
            con.setAutoCommit(false);
            boolean add = dao.add(new Repair(r.getCid(), r.getTotal(),r.getPhone(), r.getDescription()));
            int repid = dao.getLastRepair().getRepid();
            boolean addDetail = false;
            if (add) {
                ArrayList<RepairDetailDTO> repairDetail = r.getRepairDetail();
                for (RepairDetailDTO rdDTO : repairDetail) {
                    addDetail = repairDetailDAO.add(new RepairDetail(
                            repid,
                            rdDTO.getRepairServiceId(),
                            rdDTO.getAmount()
                    ));
                }
                if (addDetail) {
                    con.commit();
                    return true;
                }
            }
            con.rollback();
            return false;
        }finally {
            con.setAutoCommit(true);
        }
    }

    @Override
    public boolean deleteRepair(int id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public boolean updateRepair(RepairDTO r) throws Exception {
        return dao.update(new Repair(r.getRepid(),r.getDescription()));
    }

    @Override
    public RepairDTO searchRepair(int i) {
        return null;
    }

    @Override
    public ArrayList<RepairDTO> getAllRepair() throws Exception {
        ArrayList<RepairDTO> arrayList=new ArrayList<>();
        ArrayList<Repair> all= dao.getAll();

        for (Repair r :all) {
            RepairDTO repairDTO=new RepairDTO(
                    r.getRepid(),
                    r.getCid(),
                    r.getDate(),
                    r.getTime(),
                    r.getAmount(),
                    r.getPhone(),
                    r.getDescription()
            );
            repairDTO.setCustomerName(customerDAO.search(r.getCid()).getName());
            ArrayList<RepairDetail> repairdetailOf = repairDetailDAO.getRepairdetailOf(r.getRepid());
            int count=0;
            for (RepairDetail rd:repairdetailOf){
                count++;
            }
            repairDTO.setNoofRepair(count);
            arrayList.add(repairDTO);
        }
        return arrayList;
    }

    @Override
    public int repairToDo() throws Exception {
        return dao.repairToDo();
    }

    @Override
    public boolean finish(int i) throws Exception {
        return dao.finish(i);
    }
}
