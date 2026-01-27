package com.example.backend.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class DaySummaryDto {
    private LocalDate date;

    private List<StudyResponsedto> log;
    
    private Double totalHours;

    public DaySummaryDto(LocalDate date, List<StudyResponsedto> log, Double totalHours) {
        this.date = date;
        this.log = log;
        this.totalHours = totalHours;
    }

    public DaySummaryDto() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<StudyResponsedto> getLog() {
        return log;
    }

    public void setLog(List<StudyResponsedto> log) {
        this.log = log;
    }

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }
    

    
}
