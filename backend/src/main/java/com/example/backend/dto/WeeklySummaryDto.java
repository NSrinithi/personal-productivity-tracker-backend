package com.example.backend.dto;

import java.time.LocalDate;
import java.util.Map;

public class WeeklySummaryDto {

    private LocalDate WeekStart;

    private LocalDate WeekEnd;

    private int StudiedDays;

    private int MissedDays;

    private Double TotalHoursStudied;

    private Map<String,Double> category;

    public WeeklySummaryDto(int MissedDays, int StudiedDays, Double TotalHoursStudied, LocalDate WeekEnd, LocalDate WeekStart, Map<String, Double> category) {
        this.MissedDays = MissedDays;
        this.StudiedDays = StudiedDays;
        this.TotalHoursStudied = TotalHoursStudied;
        this.WeekEnd = WeekEnd;
        this.WeekStart = WeekStart;
        this.category = category;
    }

    public WeeklySummaryDto() {
    }


    public LocalDate getWeekStart() {
        return WeekStart;
    }

    public void setWeekStart(LocalDate WeekStart) {
        this.WeekStart = WeekStart;
    }

    public LocalDate getWeekEnd() {
        return WeekEnd;
    }

    public void setWeekEnd(LocalDate WeekEnd) {
        this.WeekEnd = WeekEnd;
    }

    public int getStudiedDays() {
        return StudiedDays;
    }

    public void setStudiedDays(int StudiedDays) {
        this.StudiedDays = StudiedDays;
    }

    public int getMissedDays() {
        return MissedDays;
    }

    public void setMissedDays(int MissedDays) {
        this.MissedDays = MissedDays;
    }

    public Double getTotalHoursStudied() {
        return TotalHoursStudied;
    }

    public void setTotalHoursStudied(Double TotalHoursStudied) {
        this.TotalHoursStudied = TotalHoursStudied;
    }

    public Map<String, Double> getCategory() {
        return category;
    }

    public void setCategory(Map<String, Double> category) {
        this.category = category;
    }

    

    

}
