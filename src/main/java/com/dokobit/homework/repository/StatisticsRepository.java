package com.dokobit.homework.repository;

import com.dokobit.homework.entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Optional<Statistics> findByIpAddress(String ipAddress);

}
