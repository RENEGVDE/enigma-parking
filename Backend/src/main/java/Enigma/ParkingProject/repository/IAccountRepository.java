package Enigma.ParkingProject.repository;

import Enigma.ParkingProject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account, Integer>{
    Account getAccountByAccountId(int id);
}
