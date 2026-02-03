package com.example.exception;

public class AlreadyAssignedException extends RuntimeException{
    public AlreadyAssignedException(){}
    public AlreadyAssignedException(String msg){
        super(msg);
    }

}
