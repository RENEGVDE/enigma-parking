package Enigma.ParkingProject.controller;

import Enigma.ParkingProject.model.Account;

import Enigma.ParkingProject.serviceinterfaces.IAccountService;
import Enigma.ParkingProject.serviceinterfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAppointmentService appointmentService;

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccountPath(@PathVariable(value = "id") int id){
        Account account = accountService.getAccountById(id);

        if(account != null){
            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    //POST at http://localhost:XXXX/account
    public ResponseEntity<Account> createAccount(@RequestBody Account newAccount) {

        accountService.addAccount(newAccount);
        String url = "account" + "/" + newAccount.getLicensePlate(); // url of the created account
        URI uri = URI.create(url);
        return new ResponseEntity(uri,HttpStatus.CREATED);
    }

    @PutMapping("{account}")
    //PUT at http://localhost:XXXX/account/{accountID}
    public ResponseEntity<Account> updateAccount(@PathVariable("account") int accountID,  @RequestParam("licenseplate") String LicensePlate, @RequestParam("firstname") String firstName, @RequestParam("lastname") String lastName, @RequestParam("phone") String phoneNo) {
        Account account = accountService.getAccountById(accountID);
        if (account == null){
            return new ResponseEntity("Please provide a valid license plate.",HttpStatus.NOT_FOUND);
        }

        account.setLicensePlate(LicensePlate);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPhoneNumber(phoneNo);
        return ResponseEntity.noContent().build();
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(@RequestParam(value = "accounts") Optional<String> licensePlate ) {
        List<Account> accounts = null;

        accounts= accountService.getAccountList();

        if(accounts != null) {
            return ResponseEntity.ok().body(accounts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deletePost(@PathVariable("id") int id) {
        appointmentService.getAllAppointmentsFromDeletedGuest(id);
        accountService.deleteAccount(id);
        // Idempotent method. Always return the same response (even if the resource has already been deleted before).
        return ResponseEntity.ok().build();
    }

}
