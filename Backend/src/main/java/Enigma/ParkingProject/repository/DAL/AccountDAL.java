package Enigma.ParkingProject.repository.DAL;

import Enigma.ParkingProject.model.Account;
import Enigma.ParkingProject.repository.IAccountDAL;
import Enigma.ParkingProject.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAL implements IAccountDAL {


    private final List<Account> accountList = new ArrayList<>();

    @Autowired
    private IAccountRepository repo;


    @Override
    public List<Account> getAccountList() {
        return repo.findAll();
    }

    @Override
    public Account getAccountById(int accountId) {
        return repo.getAccountByAccountId(accountId);
    }

    @Override
    public void deleteAccount(int accountid) {
        repo.deleteById(accountid);
    }

    @Override
    public void addAccount(Account account) {
        repo.save(account);
    }

    @Override
    public boolean updateAccount(Account account){
            repo.save(account);
            return true;
        }

}
