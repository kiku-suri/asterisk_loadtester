package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "load_generator_server")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoadGeneratorServer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serverId;

    private String serverName;

    private String ipAddress;

    private Integer sshPort;

    private String sshUser;

    private String sshPassword;

    private String status;

    private LocalDateTime createdTime;
}
