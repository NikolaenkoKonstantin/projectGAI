package com.vibelab.gai.controller;

import com.vibelab.gai.model.Fine;
import com.vibelab.gai.service.ServiceFine;
import com.vibelab.gai.util.FineErrorResponse;
import com.vibelab.gai.util.FineBadRequestException;
import com.vibelab.gai.util.FineNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FineController {
    private final ServiceFine serviceFine;

    @Autowired
    public FineController(ServiceFine serviceFine) {
        this.serviceFine = serviceFine;
    }


    @GetMapping("/fines")
    public List<Fine> fines(@RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "size", required = false) Integer size){
        return serviceFine.fines(page, size);
    }


    @PostMapping("/fines")
    public ResponseEntity<HttpStatus> addFine(@RequestBody @Valid Fine fine, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors)
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");

            throw new FineBadRequestException(errorMsg.toString());
        }

        serviceFine.addFine(fine);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @ExceptionHandler
    private ResponseEntity<FineErrorResponse> handleException(FineBadRequestException ex){
        FineErrorResponse response = new FineErrorResponse(ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/fines")
    public ResponseEntity<HttpStatus> updateFine(@RequestBody @Valid Fine fine, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for(FieldError error : errors)
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");

            throw new FineBadRequestException(errorMsg.toString());
        }
        serviceFine.updateFine(fine);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/fines")
    public ResponseEntity<HttpStatus> deleteFine(@RequestBody Fine fine){
        serviceFine.deleteFine(fine);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/fines/{id}")
    public Fine showFineId(@PathVariable("id") int id){
        return serviceFine.showFineId(id);
    }


    @ExceptionHandler
    private ResponseEntity<FineErrorResponse> handleException(FineNotFoundException ex){
        FineErrorResponse response = new FineErrorResponse("Fine with this id wasn't found", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @PatchMapping("/fines/{id}/pay")
    public ResponseEntity<HttpStatus> payFine(@PathVariable("id") int id){
        serviceFine.payFine(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PatchMapping("/fines/{id}/court")
    public ResponseEntity<HttpStatus> sendAgenda(@PathVariable("id") int id){
        serviceFine.sendAgenda(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
