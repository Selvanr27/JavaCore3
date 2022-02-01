package com.tamil.billing.exception;





public class InvalidAmtException extends RuntimeException{
    private String message;
    public InvalidAmtException(String message) {
        super(message);
        this.message=message;
    }
    public InvalidAmtException(){

    }
}
