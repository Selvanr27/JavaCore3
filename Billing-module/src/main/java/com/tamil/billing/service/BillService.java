package com.tamil.billing.service;


import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.exception.InvalidAmtException;
import com.tamil.billing.exception.InvalidDateException;
import com.tamil.billing.exception.InvalidIdException;
import com.tamil.billing.exception.InvalidNameException;

import java.util.List;
import java.util.Map;

public interface BillService {




    public BillDto addBills(BillDto dto) ;

    boolean paidBill(Long id);

    BillDto updateBill(BillDto dto) throws InvalidIdException;

    List<Bill> getAllBills();

    List<Map<String, Integer>> findTreatmentWiseAmount() throws InvalidAmtException;

    List<BillDto> billsFindByName(String name) throws InvalidNameException;


    List<BillDto> billsFindByDates(String date) throws InvalidDateException;

    List<BillDto> billAmountMoreThanGivenAmount(double amt) throws InvalidAmtException;
}
