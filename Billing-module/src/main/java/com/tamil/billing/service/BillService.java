package com.tamil.billing.service;


import com.tamil.billing.dto.BillDto;
import java.util.List;

public interface BillService {

    public BillDto createBill(BillDto dto);

    //public List<BillDto> findAll();

   // List<BillDto> findAbcByLocalityAndDoctor(String loc, String doc);
}
