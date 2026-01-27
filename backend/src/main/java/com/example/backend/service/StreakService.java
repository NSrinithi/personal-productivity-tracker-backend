package com.example.backend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.backend.dto.StreakDto;
import com.example.backend.entity.StudyLog;
import com.example.backend.entity.User;
import com.example.backend.repo.StudyLogRepo;
import com.example.backend.repo.UserRepo;


@Service
public class StreakService {

    private StudyLogRepo str;
    private UserRepo ur;

    public StreakService(StudyLogRepo str,UserRepo ur) {
        this.str = str;
        this.ur=ur;
    }
    
    public StreakDto getStreak(){
        String email=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user=ur.findByEmail(email);
        List<StudyLog> log=str.findByUserId(user.getId());
        Set<LocalDate> uniqueDays=log.stream().map(u->u.getDate()).collect(Collectors.toSet());
        LocalDate today=LocalDate.now();
        int streak=0;
        while (uniqueDays.contains(today)) { 
            streak++;
            today=today.minusDays(1);  
        }
        List<LocalDate> sortedDate=uniqueDays.stream().sorted().toList();
        int currentRun=1;
        int max=1;
        LocalDate startingDate=sortedDate.get(0);
        for(int i=0;i<sortedDate.size();i++){
            LocalDate current=sortedDate.get(i);
            if(current.equals(startingDate.plusDays(1))){
                currentRun++;
            }
            else{
                currentRun=1;
            }
            max=Math.max(currentRun,max);
            startingDate=current;
        }
        StreakDto streakDto=new StreakDto();
        streakDto.setCurrentStreak(streak);
        streakDto.setLongestStreak(max);
        streakDto.setLastStudiedDate(sortedDate.get(sortedDate.size()-1));
        return streakDto;
    }
}
