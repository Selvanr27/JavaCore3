package com.tamil.billing.service;


import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.exception.InvalidIdException;

import java.util.List;
import java.util.Map;

public interface BillService {
 //   Bill addBills(Bill bl);

    /*--------------------------<Create Bills>-----------------------------------------*/

    public BillDto addBills(BillDto dto);

   //public ResponseEntity<AppResponse<BillDto>> getAllBills();


  //  public BillDto (BillDto billDto);

   // List<BillDto>findByTreatment(String prefix);

   // Long billUpdate(Long Id, Long billAmt) throws InvalidUpdateException;

   //   List<BillDto> findByUnpaidBill(String billTreatment);

    /*-----------------------------<Mark Bill As Paid>-------------------------------*/
    boolean paidBill(Long id);

    BillDto updateBill(BillDto dto) throws InvalidIdException;

    List<Bill> getAllBills();

    List<Map<String, Integer>> findTreatmentWiseAmount();



//    List<BillDto> billStartsWith(String prefix);
}
