package com.project.kakaobank.service;

import com.project.kakaobank.domain.Remit;
import com.project.kakaobank.domain.User;
import com.project.kakaobank.repository.RemitRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RemitService {
    private final RemitRepository remitRepository;
    @Autowired
    public RemitService(RemitRepository remitRepository) {
        this.remitRepository = remitRepository;
    }

    public void save(Remit remit){
        remitRepository.save(remit);
    }
    public List<Remit> findAllBySenderId3MonthBefore(Long senderId){
        LocalDateTime now = LocalDateTime.now();
        List<Remit> remits = remitRepository.findAllBySenderId(senderId);
        for (int i = 0; i < remits.size(); i++) {
            if (remits.get(i).getCreateDateTime().isBefore(now.minusMonths(3))){
                remits.remove(remits.get(i));
            }
        }
        return remits;
    }
}
