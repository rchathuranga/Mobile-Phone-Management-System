package lk.ijse.projectmp.bo.bo.custom.impl;

import lk.ijse.projectmp.bo.bo.custom.AccountBO;
import lk.ijse.projectmp.dao.DAOFactory;
import lk.ijse.projectmp.dao.dao.custom.AccountDAO;
import lk.ijse.projectmp.dao.dao.custom.CustomerDAO;
import lk.ijse.projectmp.dto.AccountDTO;
import lk.ijse.projectmp.entity.Account;

import java.util.ArrayList;

public class AccountBOImpl implements AccountBO {
    AccountDAO dao= (AccountDAO) DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ACCOUNT);


    @Override
    public boolean addAccount(AccountDTO a) throws Exception {
        return dao.add(new Account(a.getName(),a.getUsername(),a.getPassword()));
    }

    @Override
    public boolean updateAccount(AccountDTO a) throws Exception {
        return dao.update(new Account(a.getName(),a.getUsername(),a.getPassword()));
    }

    @Override
    public AccountDTO searchAccount(String s) throws Exception {
        Account search = dao.search(s);
        if (search == null) {
            return null;
        } else{
            return new AccountDTO(search.getId(), search.getName(), search.getUsername(), search.getPassword());
        }
    }

    @Override
    public ArrayList<AccountDTO> getAllAccount() throws Exception {
        ArrayList<Account> all = dao.getAll();
        ArrayList<AccountDTO> allAccount=new ArrayList<>();
        for(Account acc:all){
            allAccount.add(new AccountDTO(acc.getId(),acc.getName(),acc.getUsername(),acc.getPassword()));
        }
        return allAccount;
    }
}
