package com.dokobit.homework.service;

import com.dokobit.homework.entity.Statistics;
import com.dokobit.homework.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Async
    public void saveStatistics(String ipAddress) {
        Optional<Statistics> existingStatistics = statisticsRepository.findByIpAddress(ipAddress);

        existingStatistics.ifPresentOrElse((stats) -> {
            Long existingUsageCount = stats.getUsageCount();
            Long uploadCount = existingUsageCount != null ? existingUsageCount + 1 : 0L;
            stats.setUsageCount(uploadCount);
            statisticsRepository.save(stats);
        }, () -> statisticsRepository.save(Statistics.builder().ipAddress(ipAddress).usageCount(0L).build()));
    }
}
