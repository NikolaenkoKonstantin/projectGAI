package com.vibelab.gai.service;

import com.vibelab.gai.model.Fine;
import com.vibelab.gai.repository.FineRepository;
import com.vibelab.gai.util.FineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ServiceFine {
    private final FineRepository fineRepository;

    @Autowired
    public ServiceFine(FineRepository fineRepository) {
        this.fineRepository = fineRepository;
    }


    public List<Fine> fines(Integer page, Integer size){
        if(page == null || size == null || size == 0)
            return fineRepository.findAll();
        else
            return fineRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public List<Fine> sortFines(Integer page, Integer size, String sort){
        if(page == null || size == null || size == 0)
            return fineRepository.findAll(Sort.by(sort));
        else
            return fineRepository.findAll(PageRequest.of(page, size, Sort.by(sort))).getContent();
    }


    @Transactional
    public void addFine(Fine fine){
        fineRepository.save(fine);
    }


    @Transactional
    public void updateFine(Fine fine){
        fineRepository.save(fine);
    }


    @Transactional
    public void deleteFine(Fine fine){
        fineRepository.delete(fine);
    }


    public Fine showFineId(int id){
        Optional<Fine> fine = fineRepository.findById(id);
        return fine.orElseThrow(FineNotFoundException::new);
    }


    @Transactional
    public void payFine(int id){
        Fine fine = showFineId(id);
        fine.setPaid(true);
        fineRepository.save(fine);
    }


    @Transactional
    public void sendAgenda(int id){
        Fine fine = showFineId(id);
        fine.setAgenda(true);
        fineRepository.save(fine);
    }
}
