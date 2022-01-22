package com.tamil.billing.service;

import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.exception.InvalidIdException;
import com.tamil.billing.exception.InvalidUpdateException;
import com.tamil.billing.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository repository;

    /*--------------------------<Create Bills>-----------------------------------------*/

  /*  @Override
    public Bill addBills(Bill dto) {


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
    }*/

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


    /*--------------------------Show All Bills-----------------------------------------*/

  /* @Override
    public ResponseEntity<AppResponse<BillDto>> getAllBills() {
        List<Bill> bobj = repository.getAllBillsDetails();
        List<BillDto> bdtoj = new ArrayList<>();
        for (int i = 0; i < bobj.size(); i++) {
            Bill bobj2 = bobj.get(i);
            BillDto dtobj = new BillDto(
                    bobj2.getId(),
                     bobj2.getPatientName(),
                    bobj2.getBillDt(),
                     bobj2.getBillTreatment(),
                     bobj2.getBillPaidDt(),
                     bobj2.getBillSts(),
                     bobj2.getBillAmt()
            );
            bdtoj.add(dtobj);
        }
        return (ResponseEntity<AppResponse<BillDto>>) bdtoj;

    }*/


    /*--------------------------TreatmentWise Bill-----------------------------------------*/

  /*  @Override
    public List<BillDto> findByTreatment(String prefix) {


        return repository.findByTreatmentName(prefix);

    }*/



    /*------------------------------Update Bill-----------------------------------------*/

@Override
public int updateBill(BillDto dto) throws InvalidUpdateException{
    Bill bill2=repository.findById(dto.getId()).orElseThrow(()->new InvalidUpdateException("Invalid Id"));
    var bill3=new Bill();

    bill3.setId(dto.getId());
    bill3.setPatientName(dto.getPatientName());
    bill3.setBillDt(dto.getBillDt());
    bill3.setBillTreatment(dto.getBillTreatment());
    bill3.setBillPaidDt(dto.getBillPaidDt());
    bill3.setBillSts(dto.getBillSts());
    bill3.setBillAmt(dto.getBillAmt());

    repository.save(bill3);
    return 1;

}

    /*-----------------------------<Mark Bill As Paid>-------------------------------*/

    public boolean paidBill(Long Id) throws InvalidIdException{
        Optional<Bill> ba=repository.findById(Id);
        Bill oldBill=ba.orElseThrow(() -> new InvalidIdException("ID is Not Valid"));
        boolean newSts=true;
        Bill bill=new Bill();
        bill.setId(oldBill.getId());
        bill.setPatientName(oldBill.getPatientName());
        bill.setBillDt(oldBill.getBillDt());
        bill.setBillTreatment(oldBill.getBillTreatment());
        bill.setBillPaidDt(oldBill.getBillPaidDt());
        bill.setBillSts(oldBill.getBillSts());
        bill.setBillAmt(oldBill.getBillAmt());

        repository.save(bill);
        return bill.getBillSts();

    }
}
