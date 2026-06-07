package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository
        extends JpaRepository<Scenario, Long> {
}
