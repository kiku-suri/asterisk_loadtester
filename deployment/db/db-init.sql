CREATE DATABASE IF NOT EXISTS asterisk_loadtest
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE asterisk_loadtest;

-- =====================================================
-- USERS
-- =====================================================

CREATE TABLE users
(
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100),
    role VARCHAR(20) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO users
(
    username,
    password_hash,
    full_name,
    role
)
VALUES
(
    'admin',
    '$2a$10$ReplaceWithBCryptHash',
    'System Administrator',
    'ADMIN'
);

-- =====================================================
-- LOAD GENERATOR SERVERS
-- =====================================================

CREATE TABLE load_generator_server
(
    server_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    server_name VARCHAR(100) NOT NULL,
    ip_address VARCHAR(50) NOT NULL,
    ssh_port INT DEFAULT 22,
    ssh_user VARCHAR(100),
    ssh_password VARCHAR(255),
    status VARCHAR(20) DEFAULT 'OFFLINE',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- DUT SERVERS
-- =====================================================

CREATE TABLE dut_server
(
    server_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    server_name VARCHAR(100) NOT NULL,
    ip_address VARCHAR(50) NOT NULL,
    ssh_port INT DEFAULT 22,
    ssh_user VARCHAR(100),
    ssh_password VARCHAR(255),
    asterisk_version VARCHAR(20),
    status VARCHAR(20) DEFAULT 'OFFLINE',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- EXTENSIONS
-- =====================================================

CREATE TABLE extension_master
(
    extension_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    extension_number VARCHAR(20) NOT NULL,
    secret VARCHAR(100),
    extension_type VARCHAR(20),
    server_id BIGINT,
    status VARCHAR(20),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_extension_dut
        FOREIGN KEY(server_id)
        REFERENCES dut_server(server_id)
);

-- =====================================================
-- SIPP SCENARIOS
-- =====================================================

CREATE TABLE sipp_scenario
(
    scenario_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    scenario_name VARCHAR(100),
    scenario_file VARCHAR(255),
    description VARCHAR(500),
    active BOOLEAN DEFAULT TRUE
);

-- =====================================================
-- TEST JOB
-- =====================================================

CREATE TABLE test_job
(
    job_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_name VARCHAR(100) NOT NULL,
    dut_id BIGINT NOT NULL,
    load_generator_id BIGINT NOT NULL,
    scenario_id BIGINT NOT NULL,
    concurrent_calls INT,
    cps INT,
    call_duration INT,
    test_duration INT,
    job_status VARCHAR(20),
    start_time DATETIME,
    end_time DATETIME,
    created_by BIGINT,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_job_dut
        FOREIGN KEY(dut_id)
        REFERENCES dut_server(server_id),

    CONSTRAINT fk_job_loadgen
        FOREIGN KEY(load_generator_id)
        REFERENCES load_generator_server(server_id),

    CONSTRAINT fk_job_scenario
        FOREIGN KEY(scenario_id)
        REFERENCES sipp_scenario(scenario_id),

    CONSTRAINT fk_job_user
        FOREIGN KEY(created_by)
        REFERENCES users(user_id)
);

-- =====================================================
-- JOB EXECUTION LOG
-- =====================================================

CREATE TABLE job_execution_log
(
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_id BIGINT,
    log_level VARCHAR(20),
    log_message TEXT,
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_job_log
        FOREIGN KEY(job_id)
        REFERENCES test_job(job_id)
);

-- =====================================================
-- ACTIVE CALL TRACKER
-- =====================================================

CREATE TABLE active_call_tracker
(
    tracker_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_id BIGINT,
    call_id VARCHAR(100),
    caller VARCHAR(20),
    callee VARCHAR(20),
    call_status VARCHAR(20),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_tracker_job
        FOREIGN KEY(job_id)
        REFERENCES test_job(job_id)
);

-- =====================================================
-- CALL LOGS
-- =====================================================

CREATE TABLE call_log
(
    call_log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_id BIGINT,
    caller VARCHAR(20),
    callee VARCHAR(20),
    start_time DATETIME,
    end_time DATETIME,
    duration_sec INT,
    sip_response INT,
    call_status VARCHAR(20),
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_call_job
        FOREIGN KEY(job_id)
        REFERENCES test_job(job_id)
);

-- =====================================================
-- SYSTEM METRICS
-- =====================================================

CREATE TABLE system_metrics
(
    metric_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_id BIGINT,
    cpu_usage DECIMAL(5,2),
    memory_usage DECIMAL(5,2),
    load_average DECIMAL(5,2),
    active_calls INT,
    registered_endpoints INT,
    collected_time DATETIME,

    CONSTRAINT fk_metric_job
        FOREIGN KEY(job_id)
        REFERENCES test_job(job_id)
);

-- =====================================================
-- REPORT EXPORTS
-- =====================================================

CREATE TABLE report_export
(
    report_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    job_id BIGINT,
    report_name VARCHAR(255),
    report_type VARCHAR(20),
    generated_by BIGINT,
    generated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_report_job
        FOREIGN KEY(job_id)
        REFERENCES test_job(job_id),

    CONSTRAINT fk_report_user
        FOREIGN KEY(generated_by)
        REFERENCES users(user_id)
);

-- =====================================================
-- INDEXES
-- =====================================================

CREATE INDEX idx_calllog_jobid
ON call_log(job_id);

CREATE INDEX idx_metrics_jobid
ON system_metrics(job_id);

CREATE INDEX idx_active_jobid
ON active_call_tracker(job_id);

CREATE INDEX idx_job_status
ON test_job(job_status);

CREATE INDEX idx_extension_number
ON extension_master(extension_number);
