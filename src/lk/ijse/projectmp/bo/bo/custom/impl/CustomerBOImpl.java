package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.CustomerBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.CustomerDAO;
import lk.ijse.projectmp.dto.CustomerDTO;
import lk.ijse.projectmp.entity.Customer;

import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO dao= (CustomerDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDTO c) throws Exception {
        return dao.add(new Customer(c.getName(),c.getContact(),c.getNic()));
    }

    @Override
    public boolean deleteCustomer(int id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDTO c) throws Exception {
        return dao.update(new Customer(c.getId(),c.getName(),c.getContact(),c.getNic()));
    }

    @Override
    public CustomerDTO searchCustomer(String name) throws Exception {
        Customer search = dao.search(name);
        return new CustomerDTO(search.getId(), search.getName(), search.getContact(), search.getNIC());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        ArrayList<CustomerDTO> allCustomer=new ArrayList<>();
        ArrayList<Customer> customers=dao.getAll();
        for(Customer c:customers){
            allCustomer.add(new CustomerDTO(c.getId(),
                    c.getName(),
                    c.getContact(),
                    c.getNIC()
            ));
        }
        return allCustomer;
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer(String date) throws Exception {
        ArrayList<CustomerDTO> allCustomer=new ArrayList<>();
        ArrayList<Customer> customers=dao.getAll(date);
        for(Customer c:customers){
            allCustomer.add(new CustomerDTO(c.getId(),
                    c.getName(),
                    c.getContact(),
                    c.getNIC()
            ));
        }
        return allCustomer;
    }

    @Override
    public CustomerDTO searchCustomer(int id) throws Exception {
        Customer search = dao.search(id);
        return new CustomerDTO(search.getId(), search.getName(), search.getContact(), search.getNIC());
    }

    @Override
    public CustomerDTO searchCustomerByContact(int contact) throws Exception {
        Customer c = dao.searchByContact(contact);
        return new CustomerDTO(c.getId(),c.getName(),c.getContact(),c.getNIC());
    }
}
