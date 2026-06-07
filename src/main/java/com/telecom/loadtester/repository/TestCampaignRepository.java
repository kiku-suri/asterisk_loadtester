package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.TestCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCampaignRepository
        extends JpaRepository<TestCampaign, Long> {

	List<TestCampaign>
    	findByStatus(String status);
}
