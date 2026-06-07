package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.CallStatusSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallStatusSummaryRepository
        extends JpaRepository<CallStatusSummary, Long> {

    List<CallStatusSummary> findByCampaignIdOrderByCollectedAtDesc(Long campaignId);
}
