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


import java.util.*;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.backend.dto.DailySummaryDto;
import com.example.backend.dto.DashBoardDto;
import com.example.backend.dto.DaySummaryDto;
import com.example.backend.dto.MonthlySummaryDto;
import com.example.backend.dto.StreakDto;
import com.example.backend.dto.WeeklySummaryDto;
import com.example.backend.service.DashService;
import com.example.backend.service.StreakService;
import com.example.backend.service.SummaryService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/study")
public class StudyLogController {

    private StudyLogService sls;
    private SummaryService summary;
    private StreakService streak;
    private DashService dash;

    

    public StudyLogController(DashService dash, StudyLogService sls, StreakService streak, SummaryService summary) {
        this.dash = dash;
        this.sls = sls;
        this.streak = streak;
        this.summary = summary;
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
    public ResponseEntity<List<StudyResponsedto>> getByidDate(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(sls.getByDate(date));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<StudyResponsedto>> searchByCategory(@PathVariable String category) {
        return ResponseEntity.ok(sls.searchByCategory(category));
    }

    @GetMapping("/user")
    public ResponseEntity<List<StudyResponsedto>> findByUser() {
        return ResponseEntity.ok(sls.findByuser());
    }

    @GetMapping("/weekSummary")
    public ResponseEntity<WeeklySummaryDto> findByUser(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(summary.summary(date));
    }

    @GetMapping("/day")
    public ResponseEntity<DaySummaryDto> getDayWise(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.status(200).body(summary.dayWise(date));
    }

    @GetMapping("/daySummary")
    public ResponseEntity<DailySummaryDto> getDaySummary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.status(200).body(summary.dailySummary(date));
    }

    @GetMapping("/streak")
    public ResponseEntity<StreakDto> getStreak() {
        return ResponseEntity.status(200).body(streak.getStreak());
    }

    @GetMapping("/monthlySummary")
    public ResponseEntity<MonthlySummaryDto> getMonthlySummary(@RequestParam int month,@RequestParam int year) {
        return ResponseEntity.status(200).body(summary.getMonthlySummary(year, month));
    }

    @GetMapping("/dash")
    public ResponseEntity<DashBoardDto> getDash() {
        System.out.println("dash");
        return ResponseEntity.status(200).body(dash.dash());
    }


    
    
}
