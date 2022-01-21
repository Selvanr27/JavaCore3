package com.tamil.billing.service;

import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.exception.InvalidUpdateException;
import com.tamil.billing.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillServiceImpl implements BillService{
    @Autowired
    private BillRepository repository;

    /*--------------------------Create Bills-----------------------------------------*/

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
        List<Bill> bobj = repository.getAllBills();
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

@Override
public List<BillDto>findByTreatment(String prefix){
    return repository.findByTreatmentName(prefix);
}


/*------------------------------Update Bill-----------------------------------------*/

public Long billUpdate(Long billAmt, Long Id)throws InvalidUpdateException{

    if (billAmt == 0) throw new InvalidUpdateException("Amount Should be Non Zero Positive " + billAmt);
    Optional<Bill> op = repository.updateBillDetails(Id);
   Bill baOld = op.orElseThrow();
    double existingBalance = baOld.getBillAmt();
    double newBalance = existingBalance +billAmt;
    Bill baNew = new Bill();
    baNew.setBillAmt((long) newBalance);
    baNew.setPatientName(baOld.getPatientName());
    baNew.setBillSts(baOld.getBillSts());
    repository.save(baNew);
    return baNew.getBillAmt();

}


}
