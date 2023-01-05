package com.vibelab.gai.controller;

import com.vibelab.gai.dto.FineDTO;
import com.vibelab.gai.dto.ResponseFineDTO;
import com.vibelab.gai.model.Fine;
import com.vibelab.gai.service.ServiceFine;
import com.vibelab.gai.util.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fines")
public class FineController {
    private final ServiceFine serviceFine;
    private final Converter fineConverter;
    private final Validation validation;


    @GetMapping
    public Page<ResponseFineDTO> fines(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                       @RequestParam(value = "sort", defaultValue = "idFine") String sort){
        return serviceFine.fines(page, size, sort).map(fineConverter::convertToResponseFineDTO);
    }


    @PostMapping
    public ResponseFineDTO addFine(@RequestBody @Valid FineDTO fineDTO, BindingResult bindingResult){
        validation.throwFineBadRequestException(bindingResult);

        return fineConverter.convertToResponseFineDTO(serviceFine.addFine(fineConverter.convertToFine(fineDTO)));
    }


    @ExceptionHandler
    private ResponseEntity<FineErrorResponse> handleException(FineBadRequestException ex){
        return new HandlerException().handleException(ex);
    }


    @PutMapping("/{id}")
    public ResponseFineDTO updateFine(@RequestBody @Valid FineDTO fineDTO,
                                      @PathVariable("id") int id, BindingResult bindingResult){
        validation.throwFineBadRequestException(bindingResult);

        return fineConverter.convertToResponseFineDTO(serviceFine.updateFine(fineConverter.convertToFine(fineDTO), id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFine(@PathVariable("id") int id){
        serviceFine.deleteFine(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{id}")
    public ResponseFineDTO getFineById(@PathVariable("id") int id){
        return fineConverter.convertToResponseFineDTO(serviceFine.getFineById(id));
    }


    @ExceptionHandler
    private ResponseEntity<FineErrorResponse> handleException(FineNotFoundException ex){
        return new HandlerException().handleException(ex);
    }


    @PatchMapping("/{id}/pay")
    public ResponseFineDTO payFine(@PathVariable("id") int id){
        return fineConverter.convertToResponseFineDTO(serviceFine.payFine(id));
    }


    @PatchMapping("/{id}/court")
    public ResponseFineDTO sendSubpoena(@PathVariable("id") int id){
        return fineConverter.convertToResponseFineDTO(serviceFine.sendSubpoena(id));
    }
}
