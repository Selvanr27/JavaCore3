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
        // just explanation I am using this strategy
        // it can be done in more efficient way

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
    public Double transferMoney(Long srcAc, Long dstAc, double amt) throws InvalidAmountException {
        if(amt <= 0) throw new InvalidAmountException("Amount Should be Non Zero Positive "+amt);
        Optional<BankAccount> op = repository.findById(srcAc);
        Optional<BankAccount> op1 = repository.findById(dstAc);

        BankAccount baOld = op.orElseThrow();
        double existingBalance = baOld.getBalance();
        double newBalance = existingBalance - amt;

        BankAccount baOld1 = op1.orElseThrow();
        double existingBalance1 = baOld1.getBalance();
        double newBalance1 = existingBalance1 + amt;


        BankAccount baNew = new BankAccount();
        baNew.setBalance(newBalance);
        baNew.setAcCrDt(baOld.getAcCrDt());
        baNew.setStatus(baOld.getStatus());
        baNew.setAcHldNm(baOld.getAcHldNm());
        baNew.setAcNum(baOld.getAcNum());
       // repository.save(baNew);

        BankAccount baNew1 = new BankAccount();
        baNew1.setBalance(newBalance1);
        baNew1.setAcCrDt(baOld1.getAcCrDt());
        baNew1.setStatus(baOld1.getStatus());
        baNew1.setAcHldNm(baOld1.getAcHldNm());
        baNew1.setAcNum(baOld1.getAcNum());
       // response.setMsg("baOld.getAcNum()"+baOld.getAcNum()+"baOld1.getAcNum()"+baOld1.getAcNum());
        repository.save(baNew1);

        return baNew1.getBalance() ;



    }

    @Override
    public List<BankAccount> findAccountByAcNum(Long acNum) {
        return repository.findByAccNum(acNum);
    }



    @Override
    public List<BankAccount> namesStartsWith(String prefix) {
        return repository.findByAcHldNmStartingWith(prefix);
    }
}
