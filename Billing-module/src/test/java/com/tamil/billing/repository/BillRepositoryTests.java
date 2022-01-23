package com.tamil.billing.repository;


import com.tamil.billing.domain.Bill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class BillRepositoryTests {

    @Autowired
        private BillRepository repository;

        @DisplayName("Bill Repository--> Creating Bill")
        @Test
        public void testCreateBill() {

            var bill = new Bill();
            bill.setId(565L);
            bill.setPatientName("abc");
            bill.setBillAmt(56L);
     bill.setBillDt(LocalDate.now());

            repository.save(bill);
        }

    @DisplayName("Bill Repository--> get Bill")
    @Test
    public void getBillTest() {

            List<Bill> bill= repository.findAll();
        Assertions.assertEquals(bill.size(),17);

    }

    @DisplayName("Bill Repository--> Update Bill")
    @Test
    public void updateBillTest() {
            Bill bill=repository.findById(13l).get();
            bill.setPatientName("selvan");
            Bill billUpdated=repository.save(bill);
            Assertions.assertNotEquals(billUpdated.getPatientName(),bill);


    }


    }

