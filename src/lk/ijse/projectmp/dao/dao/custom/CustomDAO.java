package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.SuperDAO;
import lk.ijse.projectmp.entity.Custom;

import java.util.ArrayList;

public interface CustomDAO extends SuperDAO {
    ArrayList<Custom> getOrdersofCustomer(int id) throws Exception;
    ArrayList<Custom> getCustomerInteract() throws Exception;
    ArrayList<Custom>  getMostSelling() throws Exception;
    ArrayList<Custom> getRepairDetail() throws Exception;
    ArrayList<Custom> getRepairsofCustomer(int id) throws Exception;
    ArrayList<Custom> getLeastSelling() throws Exception;
    double getOrderIncome()  throws Exception;
    double getRepairIncome()  throws Exception;
    double getPhoneExpense()  throws Exception;
    double getItemExpense()  throws Exception;
    double getRepairPartExpence()  throws Exception;
}
