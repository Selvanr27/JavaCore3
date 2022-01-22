package com.tamil.billing.controller;


import com.tamil.billing.service.BillService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BillController.class)

public class BillControlllerTests {

    @MockBean
    private BillService service;

    @Autowired
    private MockMvc mvc;

}
