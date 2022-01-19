package com.weektwo.casestudy.weekbankrestapp.service;

import com.weektwo.casestudy.weekbankrestapp.domain.BankAccount;
import com.weektwo.casestudy.weekbankrestapp.dto.AppResponse;
import com.weektwo.casestudy.weekbankrestapp.exception.InvalidAmountException;
import com.weektwo.casestudy.weekbankrestapp.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
//@Transactional annotation is used when you want the certain method/class(=all methods inside) to be executed in a transaction.
@Transactional(
        isolation = Isolation.READ_UNCOMMITTED,
        rollbackFor = SQLException.class,
        noRollbackFor = InvalidAmountException.class
)
//Spring @Service annotation is used with classes that provide some business functionalities.
//It is extremely simple to add logging support to your RESTful
// Web Service application with Spring Boot. If you are working on a Spring Boot Web project or
// a Web Services project then most likely you already do have the below dependency in your pom. ... xml file.
@Service
public class BankServiceImpl implements BankService{

    private final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

    @Autowired
    private BankRepository repository;

    @Override
    public void createNewAccount(BankAccount ba) {

        repository.save(ba);
    }

    @Override
    public ResponseEntity<AppResponse<Integer>> updateAccountDetails() {
        return null;
    }

   /* @Override
    public ResponseEntity<AppResponse<Integer>> updateAccountDetails(BankAccount baa) {
        //return repository.findAll();
        repository.updateDetails(baa);
    }*/

    @Override
    public List<BankAccount> findAllBankAccounts() {
        return repository.findAll();

    }

   /* @Override
    public List<BankAccount>.updateAccountDetails(){





    }*/

    @Override
    public boolean activateAccount(Long acNum) {
        Optional<BankAccount> op = repository.findById(acNum);
        BankAccount baOld = op.orElseThrow();
        boolean existingStatus = baOld.getStatus();
        boolean newStatus = Boolean.parseBoolean("true");
        BankAccount baNew = new BankAccount();
        baNew.setBalance(baOld.getBalance());
        baNew.setAcCrDt(baOld.getAcCrDt());
        baNew.setStatus(newStatus);
        baNew.setAcHldNm(baOld.getAcHldNm());
        baNew.setAcNum(baOld.getAcNum());
        repository.save(baNew);
        return baNew.getStatus();
    }


    @Override
    public boolean deActivateAccount(Long acNum) {
        Optional<BankAccount> op = repository.findById(acNum);
        BankAccount baOld = op.orElseThrow();
        boolean existingStatus = baOld.getStatus();
        boolean newStatus = Boolean.parseBoolean("false");
        BankAccount baNew = new BankAccount();
        baNew.setBalance(baOld.getBalance());
        baNew.setAcCrDt(baOld.getAcCrDt());
        baNew.setStatus(newStatus);
        baNew.setAcHldNm(baOld.getAcHldNm());
        baNew.setAcNum(baOld.getAcNum());
        repository.save(baNew);
        return baNew.getStatus();
    }
   /* @Override
    public double withdraw(Long acNum, double amt) throws InvalidAmountException {
        logger.info("Withdrawing Money from "+acNum +" with Amount  "+amt);
        logger.warn("Make sure amount positive");
        repository.withdraw(amt, acNum);
        return amt;
    }*/


    public double withdraw(Long acNum, double amt) throws InvalidAmountException {
        if (amt <= 0) throw new InvalidAmountException("Amount Should be Non Zero Positive " + amt);
        Optional<BankAccount> op = repository.findById(acNum);
        BankAccount baOld = op.orElseThrow();
        double existingBalance = baOld.getBalance();
        double newBalance = existingBalance - amt;
        BankAccount baNew = new BankAccount();
        baNew.setBalance(newBalance);
        baNew.setAcCrDt(baOld.getAcCrDt());
        baNew.setStatus(baOld.getStatus());
        baNew.setAcHldNm(baOld.getAcHldNm());
        baNew.setAcNum(baOld.getAcNum());
        repository.save(baNew);
        return baNew.getBalance();
    }

    @Override
    public double deposit(Long acNum, double amt) throws InvalidAmountException {


        if(amt <= 0) throw new InvalidAmountException("Amount Should be Non Zero Positive "+amt);

        Optional<BankAccount> op = repository.findById(acNum);

        BankAccount baOld = op.orElseThrow();
        double existingBalance = baOld.getBalance();
        double newBalance = existingBalance + amt;

        BankAccount baNew = new BankAccount();
        baNew.setBalance(newBalance);
        baNew.setAcCrDt(baOld.getAcCrDt());
        baNew.setStatus(baOld.getStatus());
        baNew.setAcHldNm(baOld.getAcHldNm());
        baNew.setAcNum(baOld.getAcNum());

        repository.save(baNew);

//        withdraw(acNum, 10);

        return baNew.getBalance();
    }

    @Override
    public double transferMoney(Long acNum, Long acNum2, double amt) throws InvalidAmountException {
        if (amt <= 0) throw new InvalidAmountException("Amount Should be Non Zero Positive " + amt);
        Optional<BankAccount> op = repository.findById(acNum);
        BankAccount baOld = op.orElseThrow();
        double existingBalance = baOld.getBalance();
        Boolean existingStatus = baOld.getStatus();
        if (existingStatus != true) {
            return 0;
        } else {
            double newBalance = existingBalance - amt;
            BankAccount baNew = new BankAccount();
            baNew.setBalance(newBalance);
            baNew.setAcCrDt(baOld.getAcCrDt());
            baNew.setStatus(baOld.getStatus());
            baNew.setAcHldNm(baOld.getAcHldNm());
            baNew.setAcNum(baOld.getAcNum());
            repository.save(baNew);
            Optional<BankAccount> op2 = repository.findById(acNum2);
            BankAccount baOld2 = op2.orElseThrow();
            double existingBalance2 = baOld2.getBalance();
            Boolean existingStatus1 = baOld2.getStatus();
            if (existingStatus1 != true) {
                return 0;
            } else {
                double newBalance2 = existingBalance2 + amt;
                BankAccount baNew2 = new BankAccount();
                baNew2.setBalance(newBalance2);
                baNew2.setAcCrDt(baOld2.getAcCrDt());
                baNew2.setStatus(baOld2.getStatus());
                baNew2.setAcHldNm(baOld2.getAcHldNm());
                baNew2.setAcNum(baOld2.getAcNum());
                repository.save(baNew2);
                return baNew2.getBalance();
            }
        }

    @Override
    public List<BankAccount> findAccountByAcNum(Long acNum) {

        return repository.findByAcNum(acNum);
    }



    @Override
    public List<BankAccount> namesStartsWith(String prefix) {
        return repository.findByAcHldNmStartingWith(prefix);
    }
}
