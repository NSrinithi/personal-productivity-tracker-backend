package com.example.backend.dto;
import java.util.Map;
import java.time.LocalDate;

public class WeeklySummaryDto {
    private Long userId;
    private LocalDate weekStart;
    private LocalDate weekEnd;
    private int totalHoursStudied;
    private int studiedDays;
    private int missedDays;
    private Map<String,Integer> category;

    

    public WeeklySummaryDto(){

    }

    public WeeklySummaryDto(Map<String, Integer> category, int missedDays, int studiedDays, int totalHoursStudied, Long userId, LocalDate weekEnd, LocalDate weekStart) {
        this.category = category;
        this.missedDays = missedDays;
        this.studiedDays = studiedDays;
        this.totalHoursStudied = totalHoursStudied;
        this.userId = userId;
        this.weekEnd = weekEnd;
        this.weekStart = weekStart;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public LocalDate getWeekStart() {
        return weekStart;
    }
    public void setWeekStart(LocalDate weekStart) {
        this.weekStart = weekStart;
    }
    public LocalDate getWeekEnd() {
        return weekEnd;
    }
    public void setWeekEnd(LocalDate weekEnd) {
        this.weekEnd = weekEnd;
    }
    public int getTotalHoursStudied() {
        return totalHoursStudied;
    }
    public void setTotalHoursStudied(int totalHoursStudied) {
        this.totalHoursStudied = totalHoursStudied;
    }
    public int getStudiedDays() {
        return studiedDays;
    }
    public void setStudiedDays(int studiedDays) {
        this.studiedDays = studiedDays;
    }
    public int getMissedDays() {
        return missedDays;
    }
    public void setMissedDays(int missedDays) {
        this.missedDays = missedDays;
    }

    public Map<String, Integer> getCategory() {
        return category;
    }

    public void setCategory(Map<String, Integer> category) {
        this.category = category;
    }
    
}
