package com.project.kakaobank.repository;

import com.project.kakaobank.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadYNRepository extends JpaRepository<Files, Long>  {
}
