package com.nuvyra.exercise.exception;

public class NoSuchStockException extends Exception{

    public NoSuchStockException(){
        super();
    }

    public NoSuchStockException(String errors) {
        super(errors);
    }

}
