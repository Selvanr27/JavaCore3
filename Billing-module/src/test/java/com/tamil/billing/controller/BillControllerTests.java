

       /* bill.setId(1l);
        bill.setPatientName("tester");
        bill.setBillDt(LocalDate.now());
        bill.setBillTreatment("cardiac");
        bill.setBillPaidDt(LocalDate.now());
        bill.setBillSts(false);
        bill.setBillAmt(2000l);*/
       package com.tamil.billing.controller;


        import com.tamil.billing.service.BillService;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
        import org.springframework.boot.test.mock.mockito.MockBean;
        import org.springframework.test.context.junit.jupiter.SpringExtension;
        import org.springframework.test.web.servlet.MockMvc;
        import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
        import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


        @ExtendWith(SpringExtension.class)
        @WebMvcTest(BillController.class)
        public class BillControllerTests {

            @Autowired
            private MockMvc mockMvc;

            @MockBean
            private BillService service;

            @DisplayName("Controller--> Checking Status")
            @Test
            public void testAppControllerStatus() throws Exception{
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/billing/all/"))
                        .andExpect(
                                MockMvcResultMatchers.status().isOk()

                        );
            }



        }


