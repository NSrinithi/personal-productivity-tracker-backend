package com.example.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.StudyLog;

import java.time.LocalDate;


@Repository
public interface  StudyLogRepo extends JpaRepository<StudyLog, Long>{
    List<StudyLog> findByUserIdAndDate(Long userId,LocalDate date);
    List<StudyLog> findByCategoryContaining(String category);
    
    List<StudyLog> findByUserIdAndDateBetween(Long userId,LocalDate start,LocalDate End);
    List<StudyLog> findByUserId(Long userId);
}
