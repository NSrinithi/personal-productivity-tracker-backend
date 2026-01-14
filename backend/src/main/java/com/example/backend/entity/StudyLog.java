package com.example.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class StudyLog {

    @Id
    @GeneratedValue
    private Long id;

    private String topic;
    private int hours;
    private String notes;
    private LocalDate date;
    private String category;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public StudyLog(String category, LocalDate date, int hours, Long id, String notes, String topic, User user) {
        this.category = category;
        this.date = date;
        this.hours = hours;
        this.id = id;
        this.notes = notes;
        this.topic = topic;
        this.user = user;
    }

    public StudyLog(){

    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getU() {
        return user;
    }

    public void setU(User u) {
        this.user = u;
    }

    


    
    

}
