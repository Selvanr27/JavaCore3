package com.weektwo.casestudy.weekbankrestapp.service;

import com.weektwo.casestudy.weekbankrestapp.domain.BankAccount;
import com.weektwo.casestudy.weekbankrestapp.dto.AppResponse;
import com.weektwo.casestudy.weekbankrestapp.exception.InvalidAmountException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankService {

    void createNewAccount(BankAccount ba);

    ResponseEntity<AppResponse<Integer>> updateAccountDetails();

    boolean activateAccount(Long acNum);

    boolean deActivateAccount(Long acNum);

    double withdraw(Long acNum, double amt) throws InvalidAmountException;

    double deposit(Long acNum, double amt) throws InvalidAmountException;

    Double transferMoney(Long srcAc, Long dstAc, double amt) throws InvalidAmountException;

    List<BankAccount> findAccountByAcNum(Long acNum);

    List<BankAccount> findAllBankAccounts();

    List<BankAccount> namesStartsWith(String prefix);
}