package com.tamil.billing.controller;
import com.tamil.billing.domain.Bill;
import com.tamil.billing.dto.AppResponse;
import com.tamil.billing.dto.BillDto;
import com.tamil.billing.exception.*;
import com.tamil.billing.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/billing")
@RestController
public class BillController {
    @Autowired
    private BillService service;

    /*--------------------------<Create Bills>-----------------------------------------*/


    @PostMapping()
    public ResponseEntity<AppResponse<BillDto>> addBills(@Valid  @RequestBody BillDto dto) {




            var svObj = service.addBills(dto);
            var response = new AppResponse<BillDto>();

            response.setStatus("success");
            response.setMessage("Saved successfully");
            response.setBody(svObj);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /*--------------------------Show All Bills-----------------------------------------*/

    @GetMapping("/all")
    public ResponseEntity<AppResponse<List<Bill>>>findAll() {
        var response=new AppResponse<List<Bill>>();
        response.setBody(service.getAllBills());
        response.setMessage("All Bills");
        response.setStatus("Success");
        return ResponseEntity.ok(response);
    }


    /*--------------------------Show TreatmentWise Bill-----------------------------------------*/
@GetMapping("/treatment")
public ResponseEntity<AppResponse<List<Map<String,Integer>>>>findTreatmentWise(){

    try{
        var response=new AppResponse<List<Map<String,Integer>>>();
        response.setMessage("Treatment wise Amount");
        response.setStatus("Success");
        response.setBody(service.findTreatmentWiseAmount());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }catch(InvalidAmtException e){
        var response=new AppResponse<List<Map<String,Integer>>>();
        response.setMessage(e.getMessage());
        response.setStatus("Fail");
        response.setBody(response.getBody());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}



    /*--------------------------Update All Bills-----------------------------------------*/

    @PutMapping("/update")
    public ResponseEntity<AppResponse<BillDto>>updateBills(@Valid @RequestBody BillDto dto) {



        try {
            var upBill=service.updateBill(dto);
            var response = new AppResponse<BillDto>();
            response.setMessage("Bill Updated Successfully");
            response.setStatus("Success");
            response.setBody(upBill);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (InvalidUpdateException e) {
            var response = new AppResponse<BillDto>();
            response.setMessage(e.getMessage());
            response.setStatus("Failiure");
           response.setBody(response.getBody());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
/*-----------------------------<Mark Bill As Paid>-------------------------------*/
    @PutMapping("/{Id}")
    public ResponseEntity<AppResponse<Boolean>>paidBill(@PathVariable Long Id){


        try{
            boolean paidBill=service.paidBill(Id);
            var response=new AppResponse<Boolean>();
            response.setMessage("Successfully");
            response.setStatus("Success");
            response.setBody(paidBill);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch(InvalidIdException e){
            var response=new AppResponse<Boolean>();
            response.setMessage(e.getMessage());
            response.setStatus("Fail");
            response.setBody(response.getBody());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    /*--------------------------------<Bills By Name>-------------------------------*/

    @GetMapping("/findbyname/{name}")
    public ResponseEntity<AppResponse<List<BillDto>>> billsFindByName(@PathVariable String name) {
        try {
            var response = new AppResponse<List<BillDto>>();
            response.setBody(service.billsFindByName(name));
            response.setMessage("Sucessfully Name By Bills Shown");
            response.setStatus("Success");
            return ResponseEntity.ok(response);
        } catch (InvalidNameException e) {
            var response = new AppResponse<BillDto>();

            response.setMessage(e.getMessage());
            response.setStatus("Failed");
            response.setBody(response.getBody());
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }
    }


    /*-----------------------< Find By Date >-----------------------------------------*/

    @GetMapping("/findbydate/{date}")
    public ResponseEntity<AppResponse<List<BillDto>>> billsFindByDate(@PathVariable String date) {
        try {
            var response = new AppResponse<List<BillDto>>();
            response.setBody(service.billsFindByDates(date));
            response.setMessage(" Paid Bills are Shown By Particular Date Sucessfully");
            response.setStatus("Success");
            return ResponseEntity.ok(response);
        } catch (InvalidDateException e) {
            //var response = new AppResponse<Boolean>();
            var response = new AppResponse<List<BillDto>>();
            response.setMessage(e.getMessage());
            response.setStatus("Failed");
            response.setBody(null);
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }
    }

/*---------------------------< Validation Test >----------------------------*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> messages = new HashMap<>();

        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

        for (ObjectError obj1 : errors) {
            FieldError field1 = (FieldError) obj1;

            String errorField = field1.getField();
            String errorMessage = field1.getDefaultMessage();

            messages.put(errorField, errorMessage);
        }

        return messages;
    }

}