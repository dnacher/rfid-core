package com.software.rfid.core.exceptions;

import org.springframework.http.HttpStatus;

/** Daniel Nacher 2024-08-05 */
public class NotFoundException extends RfidException {

    public NotFoundException(HttpStatus code, String message) {
        super(code, message);
    }
}
