package com.tamil.billing.service;

import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository repository;

    @Override
    public BillDto addBills(BillDto dto) {


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



   /* @Override
    public List<BillDto>getAllBills() {
        List<Bill> bil = repository.getAllBills();
        List<BillDto> dtos = new ArrayList<>();
        for (int i = 0; i < bil.size(); i++) {
            Bill bi = bil.get(i);
            BillDto dto = new BillDto(
                    bi.getId(),
                    bi.getPatientName(),
                    bi.getBillDt(),
                    bi.getBillTreatment(),
                    bi.getBillPaidDt(),
                    bi.getBillSts(),
                    bi.getBillAmt()
            );
            dtos.add(dto);
        }
        return dtos;

    }*/


}
