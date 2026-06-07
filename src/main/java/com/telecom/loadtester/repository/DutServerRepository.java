package com.telecom.loadtester.repository;

import com.telecom.loadtester.model.DutServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutServerRepository
        extends JpaRepository<DutServer, Long> {
}
