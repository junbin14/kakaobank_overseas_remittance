package com.project.kakaobank.service;

import com.project.kakaobank.domain.RecentReceiverInfoDto;
import com.project.kakaobank.domain.Remit;
import com.project.kakaobank.repository.RemitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public List<Remit> findBySenderIdByThreeMonthsAgoAfter(Long senderId){
        return remitRepository.findBySenderIdAndCreatedDateAfterOrderByCreatedDateDesc(senderId, LocalDateTime.now().minusMonths(3));
    }
    public List<RecentReceiverInfoDto> findRecentReceiverInfo(Long senderId){
        List<Remit> remits = remitRepository.findTop10BySenderIdOrderByCreatedDateDesc(senderId);
        List<RecentReceiverInfoDto> infoDtos = new ArrayList<>();

        for (int i = 0; i < remits.size(); i++){
            RecentReceiverInfoDto infoDto = new RecentReceiverInfoDto();
            infoDto.setReceiverName(remits.get(i).getReceiverName());
            infoDto.setReceiverEmail(remits.get(i).getReceiverEmail());
            infoDto.setReceiverAccountNumber(remits.get(i).getReceiverAccountNumber());
            infoDto.setReceiverAddress(remits.get(i).getReceiverAddress());
            infoDto.setCreatedDate(remits.get(i).getCreatedDate());
            infoDtos.add(infoDto);
        }

        return infoDtos;

//        for (int i = 0; i < remits.size(); i++){
//
//            for (int j = 0; j < i; j++){
//                if (remits.get(i).getReceiverEmail().equals(remits.get(j).getReceiverEmail())){
//                    remits.remove(j);
//                }
//            }
//        }
    }
}
