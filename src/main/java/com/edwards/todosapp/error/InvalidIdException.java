package com.edwards.todosapp.error;

public class InvalidIdException extends RuntimeException{

    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }
}
