package com.telecom.loadtester.service;

import com.telecom.loadtester.model.TestCampaign;
import com.telecom.loadtester.repository.TestCampaignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCampaignService {

    private final TestCampaignRepository repository;

    public TestCampaignService(
            TestCampaignRepository repository) {

        this.repository = repository;
    }

    public List<TestCampaign> findAll() {

        return repository.findAll();
    }

    public TestCampaign save(
            TestCampaign campaign) {

        return repository.save(campaign);
    }

    public TestCampaign get(
            Long id) {

        return repository.findById(id)
                .orElse(null);
    }

    public TestCampaign findById(Long id) {

    	return repository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Campaign not found"));
    }
}
