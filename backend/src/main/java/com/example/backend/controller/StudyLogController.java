package com.example.backend.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.StudyRequestdto;
import com.example.backend.dto.StudyResponsedto;
import com.example.backend.service.StudyLogService;

import jakarta.validation.Valid;

import java.util.*;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.backend.dto.WeeklySummaryDto;



@RestController
@RequestMapping("/study")
public class StudyLogController {

    private StudyLogService sls;

    public StudyLogController(StudyLogService sls) {
        this.sls = sls;
    }

    @PostMapping()
    public ResponseEntity<StudyResponsedto> add(@Valid @RequestBody StudyRequestdto s) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sls.create(s));
    }

    @GetMapping()
    public ResponseEntity<List<StudyResponsedto>> get() {
        return ResponseEntity.status(HttpStatus.OK).body(sls.getAll());
    }

    @GetMapping("/date")
    public ResponseEntity<List<StudyResponsedto>> getByidDate(@RequestParam Long userId,@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(sls.getByDate(userId, date));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<StudyResponsedto>> searchByCategory(@PathVariable String category) {
        return ResponseEntity.ok(sls.searchByCategory(category));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StudyResponsedto>> findByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(sls.findByuser(userId));
    }

    @GetMapping("/weekSummary/{userId}/{date}")
    public ResponseEntity<WeeklySummaryDto> findByUser(@PathVariable Long userId,@PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(sls.summary(userId, date));
    }

    
    
}
