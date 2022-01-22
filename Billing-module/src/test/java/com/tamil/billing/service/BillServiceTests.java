package com.tamil.billing.service;


import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.repository.BillRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BillServiceTests {

    @Mock
    private BillRepository repository;
    @InjectMocks
    private BillService service;

    @Before("ty")
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void getBillsTest(){
        List<BillDto> list=new ArrayList<BillDto>();
        BillDto bill1=new BillDto(1L,"tam", LocalDate.now(),"covid",LocalDate.now(),true,200L);
        BillDto bill2=new BillDto(2L,"tvm", LocalDate.now(),"omicron",LocalDate.now(),true,20L);
        list.add(bill1);
        list.add(bill2);
        when(repository.findAll()).thenReturn(list);
        List<Bill> billList=service.getAllBills();
        Assertions.assertEquals();






    }


}
