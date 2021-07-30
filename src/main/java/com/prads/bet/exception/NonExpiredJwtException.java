package com.prads.bet.exception;

public class NonExpiredJwtException extends RuntimeException {
    public NonExpiredJwtException(String message) {
        super(message);
    }

    public NonExpiredJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
