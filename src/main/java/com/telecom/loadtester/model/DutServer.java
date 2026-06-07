package com.telecom.loadtester.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dut_servers")
public class DutServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dut_id")
    private Long dutId;

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "ssh_port")
    private Integer sshPort = 22;

    @Column(name = "ssh_username")
    private String sshUsername;

    @Column(name = "ssh_password")
    private String sshPassword;

    @Column(name = "enabled")
    private Boolean enabled = true;

    public Long getDutId() {
        return dutId;
    }

    public void setDutId(Long dutId) {
        this.dutId = dutId;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
