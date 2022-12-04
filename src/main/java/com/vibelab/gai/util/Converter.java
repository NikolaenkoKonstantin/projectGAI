package com.vibelab.gai.util;

import com.vibelab.gai.dto.FineDTO;
import com.vibelab.gai.dto.ResponseFineDTO;
import com.vibelab.gai.model.Fine;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Converter {
    private final ModelMapper modelMapper;

    public ResponseFineDTO convertToResponseFineDTO(Fine fine){
        return modelMapper.map(fine, ResponseFineDTO.class);
    }

    public ResponseFineDTO convertToResponseFineDTO(FineDTO fineDTO){ return modelMapper.map(fineDTO, ResponseFineDTO.class); }

    public Fine convertToFine(FineDTO fineDTO){
        return modelMapper.map(fineDTO, Fine.class);
    }

}
