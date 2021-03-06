package com.tamil.billing.service;

import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.exception.*;
import com.tamil.billing.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


//@Transactional annotation is used when you want the certain method/class(=all methods inside) to be executed in a transaction.
@Transactional(
        isolation = Isolation.READ_UNCOMMITTED,
        rollbackFor = SQLException.class,
        noRollbackFor = InvalidIdException.class
)


@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository repository;

    /*--------------------------<Create Bills>-----------------------------------------*/



    @Override
    public BillDto addBills(BillDto dto)  {

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

    @Override
    public List<Bill> getAllBills(){
        return repository.findAll();
    }




    /*------------------------------Update Bill-----------------------------------------*/

@Override
public BillDto updateBill(BillDto dto) throws InvalidIdException{
    Bill bill2=repository.findById(dto.getId()).orElseThrow(()->new InvalidUpdateException("Invalid ID"));
    var bill3=new Bill();

    bill3.setId(dto.getId());
    bill3.setPatientName(dto.getPatientName());
    bill3.setBillDt(dto.getBillDt());
    bill3.setBillTreatment(dto.getBillTreatment());
    bill3.setBillPaidDt(dto.getBillPaidDt());
    bill3.setBillSts(dto.getBillSts());
    bill3.setBillAmt(dto.getBillAmt());

    repository.save(bill3);
    return dto;

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
        bill.setBillSts(newSts);
       bill.setBillAmt(oldBill.getBillAmt());

        repository.save(bill);
        return bill.getBillSts();

    }

    /*--------------------------------<Treatment Wise Bill>-------------------------------*/

   @Override
    public List <Map<String,Integer>>findTreatmentWiseAmount() throws InvalidAmtException {

       return repository.findTreatmentWiseRepo();
   }

    /*--------------------------------<Bills By Name>-------------------------------*/

    @Override
    public List<BillDto> billsFindByName(String name) throws InvalidNameException {
        var object = repository.findByNameStarting(name);
        if (object.isEmpty()) {
            throw new InvalidNameException("Bills Not Found Searched By this Name : " + name) ;
        }
        List<BillDto> bills = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            Bill bill = object.get(i);
            BillDto obj = new BillDto(
                    bill.getId(),
                    bill.getPatientName(),
                    bill.getBillDt(),
                    bill.getBillTreatment(),
                    bill.getBillPaidDt(),
                    bill.getBillSts(),
                    bill.getBillAmt()
            );
            bills.add(obj);
        }
        return bills;
    }

    /*-----------------------< Find By Date >-----------------------------------------*/

    @Override
    public List<BillDto>billsFindByDates(String date){
        var object = repository.findPaidBillsByDates(date);

        List<BillDto> bills = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            Bill bill = object.get(i);
            BillDto obj = new BillDto(
                    bill.getId(),
                    bill.getPatientName(),
                    bill.getBillDt(),
                    bill.getBillTreatment(),
                    bill.getBillPaidDt(),
                    bill.getBillSts(),
                    bill.getBillAmt()
            );
            bills.add(obj);
        }
        boolean bool=bills.isEmpty();
        if(bool==true) throw new InvalidDateException("Paid Bills Not Found Searched By this Date : " + date);
        return bills;

    }
    /*-----------------------< given amount is more than 15000 >-----------------------------------------*/
    @Override
    public List<BillDto> billAmountMoreThanGivenAmount(double amt) {
        var object = repository.billAmountMoreThanGivenAmount(amt);

        List<BillDto> bills = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            Bill bill = object.get(i);
            BillDto obj = new BillDto(
                    bill.getId(),
                    bill.getPatientName(),
                    bill.getBillDt(),
                    bill.getBillTreatment(),
                    bill.getBillPaidDt(),
                    bill.getBillSts(),
                    bill.getBillAmt()

            );
            bills.add(obj);
        }

        boolean bool=bills.isEmpty();
        if(bool==true)
            throw new InvalidAmtException("Bills Not Found Searched By Amount : "+amt);
        return bills;
    }


}



