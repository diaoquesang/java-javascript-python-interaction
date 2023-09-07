package com.example.demo.exception;

public class ProcessingFailedException extends BaseException{
    public ProcessingFailedException() {
    }

    public ProcessingFailedException(String msg) {
        super(msg);
    }
}
