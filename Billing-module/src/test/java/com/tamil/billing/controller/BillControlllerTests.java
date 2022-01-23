package com.tamil.billing.controller;


import com.tamil.billing.dto.BillDto;
import com.tamil.billing.service.BillService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BillController.class)

public class BillControlllerTests {

    @MockBean
    private BillService service;

    @Autowired
    private MockMvc mvc;

    @Test
    public void addBills_whenPostMethod()throws Exception{
        BillDto bill=new BillDto();
        bill.setId(1l);
        bill.setPatientName("tester");
        bill.setBillDt(LocalDate.now());
        bill.setBillTreatment("cardiac");
        bill.setBillPaidDt(LocalDate.now());
        bill.setBillSts(false);
        bill.setBillAmt(2000l);


        given(service.addBills(bill)).willReturn(bill);

       // mvc.perform(post("/billing")).
                mvc.perform(
                        MockMvcRequestBuilders.get("/billing")
                ).andExpect(
                        MockMvcResultMatchers.status().isOk()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath(
                                "$[0].patientName", // in the json there is list, you point to 0 element
                                Matchers.is("tester")
                        )
                );

    }
}
