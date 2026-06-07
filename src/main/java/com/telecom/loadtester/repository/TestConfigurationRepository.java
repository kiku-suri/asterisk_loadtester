package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.TestConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestConfigurationRepository
        extends JpaRepository<TestConfiguration, Long> {
}
