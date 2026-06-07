package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.CallRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CallRecordRepository
        extends JpaRepository<CallRecord, Long> {

    List<CallRecord>
    findByCampaignId(Long campaignId);
}
