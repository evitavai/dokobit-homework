package com.dokobit.homework.service;

import com.dokobit.homework.entity.Statistics;
import com.dokobit.homework.repository.StatisticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class StatisticsServiceTest {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "statistics"); //deletes all data from table before each test
    }

    @Test
    void savesStatisticsSuccessfully() {

        statisticsService.saveStatistics("127.00.00");

        List<Statistics> savedStatistics = statisticsRepository.findAll();


        assertEquals(1, savedStatistics.size());
        assertEquals("127.00.00", savedStatistics.get(0).getIpAddress());
        assertEquals(0, savedStatistics.get(0).getUsageCount());
    }

    @Test
    void incrementsUsageCountSuccessfully() {

        statisticsService.saveStatistics("127.00.00");
        statisticsService.saveStatistics("127.00.00");

        List<Statistics> savedStatistics = statisticsRepository.findAll();


        assertEquals(1, savedStatistics.size());
        assertEquals("127.00.00", savedStatistics.get(0).getIpAddress());
        assertEquals(1, savedStatistics.get(0).getUsageCount());
    }

    @Test
    void createsSeparateStatisticsRecordsSuccessfully() {

        statisticsService.saveStatistics("127.00.00");
        statisticsService.saveStatistics("125.33.22");

        List<Statistics> savedStatistics = statisticsRepository.findAll();


        assertEquals(2, savedStatistics.size());
        assertEquals("127.00.00", savedStatistics.get(0).getIpAddress());
        assertEquals("125.33.22", savedStatistics.get(1).getIpAddress());
    }
}