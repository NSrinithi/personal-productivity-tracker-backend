package com.example.backend.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.DailySummaryDto;
import com.example.backend.dto.DashBoardDto;
import com.example.backend.dto.DaySummaryDto;
import com.example.backend.dto.MonthlySnapDto;
import com.example.backend.dto.MonthlySummaryDto;
import com.example.backend.dto.StreakDto;
import com.example.backend.dto.StudyRequestdto;
import com.example.backend.dto.StudyResponsedto;
import com.example.backend.dto.WeeklySnapDto;
import com.example.backend.dto.WeeklySummaryDto;
import com.example.backend.entity.StudyLog;
import com.example.backend.entity.User;
import com.example.backend.repo.StudyLogRepo;
import com.example.backend.repo.UserRepo;

@Service
public class StudyLogService {
    private StudyLogRepo str;
    private UserRepo ur;

    public StudyLogService(StudyLogRepo str,UserRepo ur) {
        this.str = str;
        this.ur=ur;
    }

    public StudyResponsedto create(StudyRequestdto s){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User u=ur.findByEmail(email);
        StudyLog sl=new StudyLog();
        sl.setCategory(s.getCategory());
        sl.setDate(s.getDate());
        sl.setHours(s.getHours());
        sl.setNotes(s.getNotes());
        sl.setTopic(s.getTopic());
        sl.setU(u);
        StudyLog saved=str.save(sl);
        return new StudyResponsedto(saved.getId(),saved.getCategory(),saved.getDate(),saved.getHours(),saved.getTopic(),saved.getNotes());
    }

    public List<StudyResponsedto> getAll(){
        return str.findAll().stream().map(u->new StudyResponsedto(u.getId(),u.getCategory(), u.getDate(), u.getHours(),u.getTopic(),u.getNotes())).toList();
    }

    public List<StudyResponsedto> getByDate(LocalDate date){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user=ur.findByEmail(email);
        return str.findByUserIdAndDate(user.getId(), date).stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(),u.getTopic(), u.getNotes())).toList();
    }

    public List<StudyResponsedto> searchByCategory(String category){
        return str.findByCategoryContaining(category).stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(),u.getTopic(), u.getNotes())).toList();
    }

    public List<StudyResponsedto> findByuser(){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user=ur.findByEmail(email);
        return str.findByUserId(user.getId()).stream().map(u->new StudyResponsedto(u.getId(), u.getCategory(), u.getDate(), u.getHours(),u.getTopic(), u.getNotes())).toList();
    }

}
