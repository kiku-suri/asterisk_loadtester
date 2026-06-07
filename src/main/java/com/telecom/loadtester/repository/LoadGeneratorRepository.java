package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.LoadGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadGeneratorRepository
        extends JpaRepository<LoadGenerator, Long> {
}
