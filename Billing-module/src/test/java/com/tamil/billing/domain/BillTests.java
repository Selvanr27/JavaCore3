package com.tamil.billing.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

public class BillTests {



    @DisplayName("Domain --> Checking Object Creation")
    @Test
    void testObjectCreation() {
        var bill = new Bill();
        Assertions.assertNotNull(bill);
    }

    @DisplayName("Domain --> Checking getters and setters")
    @Test
    void testObjectGettersSetters() {
        var abc = new Bill();
        abc.setBillAmt(10L);
        abc.setBillPaidDt(LocalDate.of(2021,1,1));
        abc.setPatientName("abc");
        abc.setId(10L);

        Assertions.assertEquals(10, abc.getBillAmt());
        Assertions.assertEquals(
                LocalDate.of(2021,1,1),
                abc.getBillPaidDt()
        );
        Assertions.assertEquals("abc", abc.getPatientName());
        Assertions.assertEquals(10, abc.getId());
    }

}
