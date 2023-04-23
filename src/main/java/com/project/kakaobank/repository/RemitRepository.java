package com.project.kakaobank.repository;

import com.project.kakaobank.domain.Remit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RemitRepository extends JpaRepository<Remit, Long> {
    List<Remit> findBySenderIdAndCreatedDateAfterOrderByCreatedDateDesc(Long senderId, LocalDateTime createdDate);
    List<Remit> findTop10BySenderIdOrderByCreatedDateDesc(Long senderId);
}
