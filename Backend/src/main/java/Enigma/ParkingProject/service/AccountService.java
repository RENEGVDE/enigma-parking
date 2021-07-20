package Enigma.ParkingProject.service;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.IAccountDAL;
import Enigma.ParkingProject.serviceinterfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {


    @Autowired
    private IAccountDAL accountDAL;

    @Override
    public List<Account> getAccountList() {
        return accountDAL.getAccountList();
    }


    @Override
    public Account getAccountById(int accountId) {
        return accountDAL.getAccountById(accountId);
    }

    @Override
    public void deleteAccount(int accountId) {
        accountDAL.deleteAccount(accountId);
    }

    @Override
    public void addAccount(Account account) {
        accountDAL.addAccount(account);
    }

    @Override
    public boolean updateAccount(Account account) {
        Account old = this.getAccountById(account.getAccountId());
        if (old == null) {
            return false;
        }
        old.setFirstName(account.getFirstName());
        old.setLastName(account.getLastName());
        old.setLicensePlate(account.getLicensePlate());
        old.setPhoneNumber(account.getPhoneNumber());

        accountDAL.updateAccount(old);

        return true;
    }

}
