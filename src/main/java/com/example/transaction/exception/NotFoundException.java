package com.example.transaction.exception;

import org.aspectj.weaver.ast.Not;

public class NotFoundException extends RuntimeException {
    public NotFoundException (String message) {
        super(message);
    }
}
