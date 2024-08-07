package com.software.rfid.core.controller;

import java.util.Map;

import com.software.rfid.core.enums.Logger.Codigo;
import com.software.rfid.core.exceptions.RfidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** Daniel Nacher 2024-03-26 */

public class ResponseFactory {

    public static ResponseEntity<?> createResponseEntity(Map<String, Object> body, String errormessage, HttpStatus httpStatus) {
        body.put("status", httpStatus.value());
        if(errormessage!=null) {
            body.put("error", errormessage);
        }
        return new ResponseEntity<>(body, httpStatus);
    }

    public static ResponseEntity<?> handleErrorCodes(Map<String, Object> body, final Codigo error, RfidException ex) {
        if(error == null && ex == null) {
            return createResponseEntity(body, "Error inesperado", HttpStatus.BAD_REQUEST);
        } else if(ex!= null){
            return createResponseEntity(body, ex.getMessage(), ex.getCode());
        } else {
            return createResponseEntity(body, error.getError(), error.getHttpStatus());
        }
    }
}
