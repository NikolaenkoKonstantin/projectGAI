package com.vibelab.gai.dao;

import com.vibelab.gai.model.Fine;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FineDAO {
    private List<Fine> fineList = new ArrayList<>();
    private int index = 0;


    //Для примера 3 штрафа инициализировал сразу?, чтобы в Postman был пример JSON
    {
        String dateFine = "2022.01.30";
        String time = "17:00";
        String dateDeadline = "2022.02.27";
        fineList.add(new Fine(index++, "A212121212121212A", "Ivanov I.I.", "Ivanov I.I.",
                dateFine, time, 100, false, false, dateDeadline));

        fineList.add(new Fine(index++, "B313131313131313B", "Petrov P.P.", "Petrov P.P.",
                dateFine, time, 200, true, false, dateDeadline));

        fineList.add(new Fine(index++, "C313131313131313C", "Sidorov S.S.", "Sidorov S.S.",
                dateFine, time, 300, false, true, dateDeadline));
    }


    public List<Fine> fines(){
        return fineList;
    }


    public void addFine(Fine fine){
        fineList.add(fine);
    }


    public boolean updateFine(Fine fine){
        for(int i = 0; i < fineList.size(); i++){
            Fine tempFine = fineList.get(i);
            if(fine.getIdFine() == tempFine.getIdFine()){
                fineList.remove(tempFine);
                fineList.add(fine);
                return true;
            }
        }

        return false;
    }


    public void deleteFine(Fine fine){
        fineList.remove(fine);
    }


    public Optional<Fine> showFineId(int id){
        Fine showFine = null;

        for(int i = 0; i < fineList.size(); i++){
            Fine tempFine = fineList.get(i);
            if(id == tempFine.getIdFine()){
                showFine = tempFine;
                break;
            }
        }

        return Optional.ofNullable(showFine);
    }


    public boolean payFine(int id){
        for(int i = 0; i < fineList.size(); i++){
            Fine tempFine = fineList.get(i);
            if(id == tempFine.getIdFine()){
                tempFine.setPaid(true);
                return true;
            }
        }

        return false;
    }


    public boolean sendAgenda(int id){
        for(int i = 0; i < fineList.size(); i++){
            Fine tempFine = fineList.get(i);
            if(id == tempFine.getIdFine()){
                tempFine.setAgenda(true);
                return true;
            }
        }

        return false;
    }
}
