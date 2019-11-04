package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.CustomerDTO;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
     boolean addCustomer(CustomerDTO c)throws Exception;
     boolean deleteCustomer(int id) throws Exception;
     boolean updateCustomer(CustomerDTO c) throws Exception;
     CustomerDTO searchCustomer(String id) throws Exception;
     ArrayList<CustomerDTO> getAllCustomer() throws Exception;
     ArrayList<CustomerDTO> getAllCustomer(String date) throws Exception;
     CustomerDTO searchCustomer(int id) throws Exception;
     CustomerDTO searchCustomerByContact(int contact) throws Exception;
}
