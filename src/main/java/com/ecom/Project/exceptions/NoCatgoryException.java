package com.ecom.Project.exceptions;

public class NoCatgoryException extends RuntimeException{
    public NoCatgoryException() {
    }

    public NoCatgoryException(String message) {
        super(message);
    }

}
