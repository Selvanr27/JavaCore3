package com.tamil.billing.controller;

import com.tamil.billing.dto.BillDto;
import com.tamil.billing.dto.AppResponse;
import com.tamil.billing.service.BillService;
import com.tamil.billing.dto.AppResponse;
import com.tamil.billing.dto.BillDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/bill")
@RestController
public class BillController {

    @Autowired
    private BillService service;

    @PostMapping
    public ResponseEntity<AppResponse<BillDto>> createBill(@RequestBody BillDto dto) {

        var bl = service.createBill(dto);

        var response = new AppResponse<BillDto>();
        response.setStatus("success");
        response.setMessage("Bill Generated successfully");
        response.setBody(bl);

        return ResponseEntity.ok(response);
    }


}