package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.SystemResourceMetric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemResourceMetricRepository
        extends JpaRepository<SystemResourceMetric, Long> {

    List<SystemResourceMetric> findByCampaignIdOrderByCollectedAtDesc(Long campaignId);
}
