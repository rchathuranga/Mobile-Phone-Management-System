package lk.ijse.projectmp.bo.bo.custom;

import lk.ijse.projectmp.bo.SuperBO;
import lk.ijse.projectmp.dto.AccountDTO;

import java.util.ArrayList;

public interface AccountBO extends SuperBO {
    boolean addAccount(AccountDTO account) throws Exception;
    boolean updateAccount(AccountDTO account) throws Exception;
    AccountDTO searchAccount(String s) throws Exception;
    ArrayList<AccountDTO> getAllAccount() throws Exception;
}
