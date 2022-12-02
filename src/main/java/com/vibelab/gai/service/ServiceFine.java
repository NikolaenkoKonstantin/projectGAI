package com.vibelab.gai.service;

import com.vibelab.gai.dao.FineDAO;
import com.vibelab.gai.model.Fine;
import com.vibelab.gai.util.FineNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceFine {
    private final FineDAO fineDAO;

    @Autowired
    public ServiceFine(FineDAO fineDAO) {
        this.fineDAO = fineDAO;
    }


    public List<Fine> fines(){
        return fineDAO.fines();
    }


    public void addFine(Fine fine){
        fineDAO.addFine(fine);
    }


    public void updateFine(Fine fine){
        if(!fineDAO.updateFine(fine))
            throw new FineNotFoundException();
    }


    public void deleteFine(Fine fine){
        fineDAO.deleteFine(fine);
    }


    public Fine showFineId(int id){
        Optional<Fine> fine = fineDAO.showFineId(id);
        return fine.orElseThrow(FineNotFoundException::new);
    }


    public void payFine(int id){
        if(!fineDAO.payFine(id))
            throw new FineNotFoundException();
    }


    public void sendAgenda(int id){
        if(!fineDAO.sendAgenda(id))
            throw new FineNotFoundException();
    }
}
