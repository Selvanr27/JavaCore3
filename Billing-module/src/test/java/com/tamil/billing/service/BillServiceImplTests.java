package com.tamil.billing.service;

import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class BillServiceImplTests {

    @DisplayName("Testing Account Creation Method")
    @Test
    public void testCreateBill() {
        Bill bill = new Bill();
        bill.setId(1L);
        bill.setPatientName("Dhinesh");
        bill.setBillDt(LocalDate.of(2021, 12, 12));
        bill.setBillSts(true);
        bill.setBillAmt(200L);
        bill.setBillPaidDt(LocalDate.of(2021, 12, 14));
        bill.setBillTreatment("Covid");
        Assertions.assertEquals(1, bill.getId());
        Assertions.assertEquals("Dhinesh", bill.getPatientName());
        Assertions.assertEquals(LocalDate.of(2021, 12, 12), bill.getBillDt());
        Assertions.assertEquals(true, bill.getBillSts());
        Assertions.assertEquals(LocalDate.of(2021, 12, 14), bill.getBillPaidDt());
        Assertions.assertEquals(200L, bill.getBillAmt());
        Assertions.assertEquals("Covid", bill.getBillTreatment());
    }


    @DisplayName("Testing MarkBills Method")
    @Test
    public void testMarkBill() {
        Bill bill = new Bill();
        bill.setBillSts(false);
        boolean existStatus = bill.getBillSts();
        Assertions.assertEquals(false, existStatus);
    }

    @DisplayName("Testing All Bills Method")
    @Test
    public void testAllBills() {
        List<Bill> object = new ArrayList<>();
        List<BillDto> bills = new ArrayList<>();
        for (int i = 0; i < object.size(); i++) {
            Bill bill = object.get(i);
            bill.setId(1L);
            bill.setPatientName("Dhinesh");
            bill.setBillDt(LocalDate.of(2021, 12, 12));
            bill.setBillSts(true);
            bill.setBillAmt(200L);
            bill.setBillPaidDt(LocalDate.of(2021, 12, 14));
            bill.setBillTreatment("Covid");
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
        Assertions.assertNotNull(bills);

    }

    @DisplayName("Testing Update Method")
    @Test
    public void testUpdateMethod() {
        BillDto obj = new BillDto();
        obj.setId(42);
        var bill = new BillDto();
        bill.setId(1L);
        bill.setPatientName("Dhinesh");
        bill.setBillDt(LocalDate.of(2021, 12, 12));
        bill.setBillSts(true);
        bill.setBillAmt(200L);
        bill.setBillPaidDt(LocalDate.of(2021, 12, 14));
        bill.setBillTreatment("Covid");
        Assertions.assertEquals(78, bill.getId());
    }
}