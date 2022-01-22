package com.tamil.billing.controller;

import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.AppResponse;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.exception.InvalidBillException;
import com.tamil.billing.exception.InvalidIdException;
import com.tamil.billing.exception.InvalidPrefixException;
import com.tamil.billing.exception.InvalidUpdateException;
import com.tamil.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;




@RequestMapping("/billing")
@RestController
public class BillController {
    @Autowired
    private BillService service;

    /*--------------------------<Create Bills>-----------------------------------------*/


    @PostMapping()
    public ResponseEntity<AppResponse<BillDto>> addBills(@RequestBody BillDto dto) {

        var svObj = service.addBills(dto);
        var response = new AppResponse<BillDto>();

        try {

            response.setStatus("success");
            response.setMessage("Saved successfully");
            response.setBody(svObj);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (InvalidBillException e) {

            response.setMessage(e.getMessage());
            response.setStatus("fail");
            response.setBody(svObj);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
    }

    /*--------------------------Show All Bills-----------------------------------------*/

   /* @GetMapping("/all")
    public ResponseEntity<AppResponse<BillDto>>getAllBills(){
        var response = new AppResponse<BillDto>();
        try {
            return service.getAllBills();
           // return null;

    }catch (InvalidBillException e) {

        response.setMessage(e.getMessage());
        response.setStatus("fail");
        response.setBody(response.getBody());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
    }*/

   /* @GetMapping("/{prefix}")
    public ResponseEntity<AppResponse<List<BillDto>>> accountsStartWith(@PathVariable String prefix) {
        var response = new AppResponse<List<BillDto>>();
        try{
        response.setMessage("Bill list");
        response.setStatus("success");
        response.setBody(service.findByTreatment(prefix));

            return new ResponseEntity<>(response, HttpStatus.OK);
    }catch(InvalidPrefixException e) {
       response.setMessage(e.getMessage());
       response.setStatus("Fail");
       response.setBody(response.getBody());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        }*/
    @PutMapping("/updatebill")
    public ResponseEntity<AppResponse<Integer>>updateBill(@RequestBody BillDto dto) {

   var upBill=service.updateBill(dto);
        var response = new AppResponse<Integer>();
        try {


            response.setMessage("Bill Updated Successfully");
            response.setStatus("Success");
            response.setBody(upBill);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (InvalidUpdateException e) {

            response.setMessage(e.getMessage());
            response.setStatus("Fail");
            response.setBody(response.getBody());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
/*-----------------------------<Mark Bill As Paid>-------------------------------*/
    @PutMapping("/{Id}")
    public ResponseEntity<AppResponse<Boolean>>paidBill(@PathVariable Long Id){
        boolean paidBill=service.paidBill(Id);
        var response=new AppResponse<Boolean>();
        try{

            response.setMessage("Successfully");
            response.setStatus("Success");
            response.setBody(paidBill);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(InvalidIdException e){
            response.setMessage(e.getMessage());
            response.setStatus("Fail");
            response.setBody(response.getBody());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}