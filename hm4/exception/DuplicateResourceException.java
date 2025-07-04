package com.ho.exception;

public class DuplicateResourceException extends RuntimeException 
{

    public DuplicateResourceException() 
    {
        super();
    }

    public DuplicateResourceException(String message) 
    {
        super(message);
    }

    public DuplicateResourceException(String message, Throwable cause) 
    {
        super(message, cause);
    }
}
