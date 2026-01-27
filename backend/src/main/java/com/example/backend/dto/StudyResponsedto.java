package com.example.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudyResponsedto {
    private LocalDate date;

    private String topic;

    private String category;

    private Double hours;

    private String notes;


    private Long id;

   

    public StudyResponsedto() {
    }

    public StudyResponsedto(Long id,String category, LocalDate date, Double hours,  String notes, String topic) {
        this.category = category;
        this.date = date;
        this.hours = hours;
        this.id = id;
        this.notes = notes;
        this.topic = topic;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    
}
