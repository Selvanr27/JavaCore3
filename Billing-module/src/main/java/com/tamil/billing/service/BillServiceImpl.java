package com.tamil.billing.service;

import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository repository;

    @Override
    public BillDto createBill(BillDto dto) {

        var bill = new Bill();
        bill.setId(dto.getId());
        bill.setPatientName(dto.getPatientName());
        bill.setBillDt(dto.getBillDt());
        bill.setBillTreatment(dto.getBillTreatment());
        bill.setBillPaidDt(dto.getBillPaidDt());
        bill.setBillSts(dto.getBillSts());
        bill.setBillAmt(dto.getBillAmt());
repository.save(bill);
        return dto;
    }


}
