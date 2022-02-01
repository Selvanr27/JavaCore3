package com.tamil.billing.exception;

import com.tamil.billing.dto.BillDto;

public class InvalidIdException extends RuntimeException{
    private String message;

    public InvalidIdException(String message) {
        super(message);
        this.message=message;
    }
    public  InvalidIdException (){

    }


}
