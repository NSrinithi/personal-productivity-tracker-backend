package com.example.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudyRequestdto {

    private LocalDate date;

    @NotBlank(message="Topic cannot be empty")
    private String topic;

    @NotBlank(message="Category cannot be empty")
    private String category;

    @NotNull
    private Double hours;

    @NotBlank(message="Notes cannot be empty")
    private String notes;


    public StudyRequestdto(String category, LocalDate date, Double hours, String notes, String topic) {
        this.category = category;
        this.date = date;
        this.hours = hours;
        this.notes = notes;
        this.topic = topic;
    }

    public StudyRequestdto() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    




}
