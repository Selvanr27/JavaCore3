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

    @GetMapping("/all")
    public List<Bill>findAll()
    {
        return service.getAllBills();
    }


    /*--------------------------Show TreatmentWise Bill-----------------------------------------*/
@GetMapping("/treatment")
public ResponseEntity<AppResponse<List<Map<String,Integer>>>>findTreatmentWise(){
    var response=new AppResponse<List<Map<String,Integer>>>();
    try{
        response.setMessage("Treatment wise Amount");
        response.setStatus("Success");
        response.setBody(service.findTreatmentWiseAmount());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }catch(InvalidAmtException e){
        response.setMessage(e.getMessage());
        response.setStatus("Fail");
        response.setBody(response.getBody());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}



    /*--------------------------Update All Bills-----------------------------------------*/

    @PutMapping("/update")
    public ResponseEntity<AppResponse<BillDto>>updateBills(@Valid @RequestBody BillDto dto) {

   var upBill=service.updateBill(dto);
        var response = new AppResponse<BillDto>();
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