package com.vibelab.gai.util;

import com.vibelab.gai.model.Fine;
import com.vibelab.gai.repository.FineRepository;
import com.vibelab.gai.service.ServiceFine;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduledTask {
    private final FineRepository fineRepository;

    @Scheduled(cron = "@daily")
    public void testing() {
        List<Fine> fineList = fineRepository.findAll();

        fineList.forEach(fine -> {
            LocalDate datePayment = fine.getDatePayment();

            if((datePayment == null && fine.getDateDeadline().compareTo(LocalDate.now()) < 0)
                    || (datePayment != null && datePayment.compareTo(fine.getDateDeadline()) > 0)){
                fine.setOverdue(true);
                fineRepository.save(fine);
            }
        });
    }

}
