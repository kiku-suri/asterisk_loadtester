package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.SystemMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemMetricsRepository
        extends JpaRepository<SystemMetrics, Long> {

    List<SystemMetrics>
    findByCampaignIdOrderByCollectedAtDesc(
            Long campaignId);
}
