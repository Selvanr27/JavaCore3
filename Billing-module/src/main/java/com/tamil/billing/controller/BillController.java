package com.tamil.billing.controller;

import com.tamil.billing.dto.AppResponse;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/billing")
@RestController
public class BillController {


    @Autowired
    private BillService service;
    @PostMapping
    public ResponseEntity<AppResponse<BillDto>> createAbc(@RequestBody BillDto dto) {

        var svObj = service.addBills(dto);
        var response = new AppResponse<BillDto>();
        response.setStatus("success");
        response.setMessage("Saved successfully");
        response.setBody(svObj);

        return ResponseEntity.ok(response);
    }
  /*  @PostMapping
    public ResponseEntity<AppResponse<BillDto>> createBill(@RequestBody BillDto dto) {

        var bl = service.createBill(dto);
        var response = new AppResponse<BillDto>();
        response.setStatus("success");
        response.setMessage("Bill Generated successfully");
        response.setBody(bl);

        return ResponseEntity.ok(response);
    }*/


}