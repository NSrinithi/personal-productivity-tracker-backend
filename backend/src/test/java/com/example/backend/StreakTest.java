package com.example.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.example.backend.dto.StreakDto;
import com.example.backend.service.StreakService;

public class StreakTest {
    
    StreakService ss=new StreakService(null,null);

    @Test
    void testStreak(){
        Set<LocalDate> dates=Set.of(LocalDate.now(),LocalDate.now().minusDays(1),LocalDate.now().minusDays(2));
        StreakDto result=ss.getStreakForTesting(dates);
        assertEquals(3,result.getCurrentStreak());
        assertEquals(3,result.getLongestStreak());
    }

    @Test
    void testStreakDiff(){
        Set<LocalDate> dates=Set.of(LocalDate.now(),LocalDate.now().minusDays(1),LocalDate.now().minusDays(4));
        StreakDto result=ss.getStreakForTesting(dates);
        assertEquals(2,result.getCurrentStreak());
        assertEquals(2,result.getLongestStreak());
    }
}
