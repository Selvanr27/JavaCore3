package com.tamil.billing.service;


import com.tamil.billing.dto.BillDto;
import com.tamil.billing.exception.InvalidUpdateException;

import java.util.List;

public interface BillService {
 //   Bill addBills(Bill bl);

    public BillDto addBills(BillDto dto);

  // public ResponseEntity<AppResponse<BillDto>> getAllBills();


  //  public BillDto (BillDto billDto);

    List<BillDto>findByTreatment(String prefix);

    Long billUpdate(Long Id, Long billAmt) throws InvalidUpdateException;


}
