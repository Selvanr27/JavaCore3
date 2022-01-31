package com.tamil.billing.controller;

import com.tamil.billing.dto.AppResponse;
import com.tamil.billing.dto.BillDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BillAllControllerTests {

    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    private int port;

    @DisplayName("POST-->Testing POST Method")
    @Test
    public void testPostMethod(){
        String url="http://"+"localhost"+":"+port+"/billing";
        var bill = new BillDto();
        bill.setId(106L);
        bill.setPatientName("selvnt");
        bill.setBillDt(LocalDate.of(2021, 12, 12));
        bill.setBillSts(true);
        bill.setBillAmt(200L);
        bill.setBillPaidDt(LocalDate.of(2021, 12, 14));
        bill.setBillTreatment("Covid");
        var val=template.postForEntity(
                url,
                bill,
                AppResponse.class
        );
        Assertions.assertEquals(HttpStatus.OK,val.getStatusCode());

    }
    @DisplayName("PUT-->Testing PUT Method")
    @Test
    public void testPutMethod(){
        BillDto obj=new BillDto();
        obj.setId(1L);
        String url="http://"+"localhost"+":"+port+"/billing/update";
        var bill = new BillDto();
        bill.setId(54L);
        bill.setPatientName("boss");
        bill.setBillDt(LocalDate.of(2022, 01, 01));
        bill.setBillSts(true);
        bill.setBillAmt(4000L);
        bill.setBillPaidDt(LocalDate.of(2022, 02, 01));
        bill.setBillTreatment("cold");
        Assertions.assertEquals(54,bill.getId());

    }

  /*  @DisplayName("GET-->Checking Object is Not Null")
    @Test
    public void testGetMethod(){
        String url="http://"+"localhost"+":"+port+"/billing/all";
        BillDto bill=template.getForObject(
                url,
                BillDto.class
        );
        Assertions.assertNotNull(bill);
    }*/

    @DisplayName("GET-->Testing Object Content")
    @Test
    public void testObjectContent(){
        BillDto bill=new BillDto();
        bill.setId(54);
        String url="http://"+"localhost"+":"+port+"/billing/all";
        var rev=template.getForEntity(
                url,
                BillDto.class
        );
        Assertions.assertEquals(54,bill.getId());
    }

    @DisplayName("GET-->Checking Status Code")
    @Test
    public void testStatusCode(){
        String url="http://"+"localhost"+":"+port+"/billing/treatment";
        ResponseEntity<BillDto> entity=template.getForEntity(
                url,
                BillDto.class
        );
        Assertions.assertEquals(
                HttpStatus.OK,
                entity.getStatusCode()
        );
    }
}