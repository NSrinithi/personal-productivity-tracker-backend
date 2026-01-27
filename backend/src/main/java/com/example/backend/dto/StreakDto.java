package com.example.backend.dto;

import java.time.LocalDate;

public class StreakDto {
    
    private int longestStreak;
    private int currentStreak;

    private LocalDate lastStudiedDate;

    public StreakDto(int longestStreak, int currentStreak, LocalDate lastStudiedDate) {
        this.longestStreak = longestStreak;
        this.currentStreak = currentStreak;
        this.lastStudiedDate = lastStudiedDate;
    }

    public StreakDto() {
    }

    public int getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(int longestStreak) {
        this.longestStreak = longestStreak;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

    public LocalDate getLastStudiedDate() {
        return lastStudiedDate;
    }

    public void setLastStudiedDate(LocalDate lastStudiedDate) {
        this.lastStudiedDate = lastStudiedDate;
    }

    
}
