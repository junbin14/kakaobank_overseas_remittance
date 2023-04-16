package com.project.kakaobank.repository;

import com.project.kakaobank.domain.Remit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemitRepository extends JpaRepository<Remit, Long> {
}
