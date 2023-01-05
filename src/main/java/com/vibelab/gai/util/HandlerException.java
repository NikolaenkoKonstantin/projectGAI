package com.vibelab.gai.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HandlerException {

    public ResponseEntity<FineErrorResponse> handleException(FineBadRequestException ex){
        FineErrorResponse response = new FineErrorResponse(ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<FineErrorResponse> handleException(FineNotFoundException ex){
        FineErrorResponse response = new FineErrorResponse("Fine with this id wasn't found", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
