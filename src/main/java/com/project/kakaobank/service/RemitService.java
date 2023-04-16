package com.project.kakaobank.service;

import com.project.kakaobank.domain.Remit;
import com.project.kakaobank.repository.RemitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
