package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.CampaignStatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignStatisticsRepository
        extends JpaRepository<CampaignStatistics, Long> {

    List<CampaignStatistics>
    findByCampaignId(Long campaignId);
}
