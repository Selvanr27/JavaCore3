package com.tamil.billing.service;


import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;

import java.util.List;

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

    int updateBill(BillDto dto);

    List<Bill> getAllBills();
}
