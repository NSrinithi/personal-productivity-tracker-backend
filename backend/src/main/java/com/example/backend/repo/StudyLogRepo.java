package com.example.backend.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.StudyLog;
import com.example.backend.entity.User;


@Repository
public interface  StudyLogRepo extends JpaRepository<StudyLog, Long>{
    List<StudyLog> findByUserIdAndDate(Long userId,LocalDate date);
    List<StudyLog> findByCategoryContaining(String category);
    
    List<StudyLog> findByUserIdAndDateBetween(Long userId,LocalDate start,LocalDate End);
    Page<StudyLog> findByUser(User u,Pageable pageable);
    List<StudyLog> findByUserId(Long userId);
}
