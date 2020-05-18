package org.cap.drinkanddelight.exceptions;

public class RMOrderIDDoesNotExistException extends RuntimeException{
    public RMOrderIDDoesNotExistException(String msg){
        super(msg);
    }
}
