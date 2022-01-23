package com.tamil.billing.service;


import com.tamil.billing.dto.BillDto;
import com.tamil.billing.repository.BillRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BillServiceTests {

    @Mock
    private BillRepository repository;
    @InjectMocks
    private BillService service;

    @Test
   public void whenSaveBill_shouldBeReturnBill(){
        BillDto bill=new BillDto();
        bill.setId(1l);
        bill.setPatientName("tester");
        bill.setBillDt(LocalDate.now());
        bill.setBillTreatment("cardiac");
        bill.setBillPaidDt(LocalDate.now());
        bill.setBillSts(false);
        bill.setBillAmt(2000l);

        //mocikto.when(repository.

                var apps = service.getAllBills(); // you called the function 1 time
        Assertions.assertEquals(apps.size(), 2);

        // I want to make sure, actually repository method has called
        // so that I am verifying it
        Mockito.verify(
                repository,
                Mockito.times(1) // function should be called 1 times
        );










    }


}
