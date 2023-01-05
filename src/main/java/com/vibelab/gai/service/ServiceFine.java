package com.vibelab.gai.service;

import com.vibelab.gai.model.Fine;
import com.vibelab.gai.repository.FineRepository;
import com.vibelab.gai.util.FineNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ServiceFine {
    private final FineRepository fineRepository;


    public Page<Fine> fines(Integer page, Integer size, String sort){
        return fineRepository.findAll(PageRequest.of(page, size, Sort.by(sort)));
    }


    @Transactional
    public Fine addFine(Fine fine){
        enrichFine(fine);
        return fineRepository.save(fine);
    }

    private void enrichFine(Fine fine){
        fine.setDateTimeFine(LocalDateTime.now());
        fine.setDateDeadline(LocalDate.now().plusMonths(1));
    }


    private Fine updateFineDTO(Fine updateFine, int id){
        Fine fine = getFineById(id);
        updateFine.setIdFine(id);
        updateFine.setDateTimeFine(fine.getDateTimeFine());
        updateFine.setDateDeadline(fine.getDateDeadline());

        return updateFine;
    }

    @Transactional
    public Fine updateFine(Fine updateFine, int id){
        return fineRepository.save(updateFineDTO(updateFine, id));
    }


    @Transactional
    public void deleteFine(int id){
        fineRepository.deleteById(id);
    }


    public Fine getFineById(int id){
        Optional<Fine> fine = fineRepository.findById(id);
        return fine.orElseThrow(FineNotFoundException::new);
    }


    @Transactional
    public Fine payFine(int id){
        Fine fine = getFineById(id);
        fine.setPaid(true);
        fine.setDatePayment(LocalDate.now());
        return fineRepository.save(fine);
    }


    @Transactional
    public Fine sendSubpoena(int id){
        Fine fine = getFineById(id);
        fine.setSubpoena(true);
        return fineRepository.save(fine);
    }
}
