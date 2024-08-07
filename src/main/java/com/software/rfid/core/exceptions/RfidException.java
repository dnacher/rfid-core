package com.software.rfid.core.exceptions;

import org.springframework.http.HttpStatus;

/** Daniel Nacher 2024-08-05 */
public class RfidException extends RuntimeException {

    private HttpStatus code;

    public RfidException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
