package com.telecom.loadtester.model;

import jakarta.persistence.*;

@Entity
@Table(name = "load_generators")
public class LoadGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "generator_id")
    private Long generatorId;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "max_calls")
    private Integer maxCalls;

    @Column(name = "status")
    private String status;

    @Column(name = "ssh_port")
    private Integer sshPort;

    @Column(name = "ssh_username")
    private String sshUsername;

    @Column(name = "ssh_password")
    private String sshPassword;

    public Long getGeneratorId() {
        return generatorId;
    }

    public void setGeneratorId(Long generatorId) {
        this.generatorId = generatorId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getMaxCalls() {
        return maxCalls;
    }

    public void setMaxCalls(Integer maxCalls) {
        this.maxCalls = maxCalls;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSshPort() {
    return sshPort;
    }

    public void setSshPort(Integer sshPort) {
    this.sshPort = sshPort;
    }

    public String getSshUsername() {
    return sshUsername;
    } 

    public void setSshUsername(String sshUsername) {
    this.sshUsername = sshUsername;
    }

    public String getSshPassword() {
    return sshPassword;
    }

    public void setSshPassword(String sshPassword) {
    this.sshPassword = sshPassword;
    }
}
