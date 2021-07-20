package Enigma.ParkingProject.serviceinterfaces;

import Enigma.ParkingProject.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getAccountList();

    Account getAccountById(int accountId);

    void deleteAccount(int accountId);

    void addAccount(Account account);

    boolean updateAccount(Account account);
}
