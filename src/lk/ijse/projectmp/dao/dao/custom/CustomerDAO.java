package lk.ijse.projectmp.dao.dao.custom;

import lk.ijse.projectmp.dao.CrudDAO;
import lk.ijse.projectmp.entity.Customer;

import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,Integer> {
     ArrayList<Customer> getAll(String date) throws Exception;
     Customer search(String s) throws Exception;
     Customer searchByContact(int contact) throws Exception;
}
