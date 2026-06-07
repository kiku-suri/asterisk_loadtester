package com.telecom.loadtester.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "extension_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtensionMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long extensionId;

    private String extensionNumber;

    private String secret;

    private String extensionType;

    private String status;

    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "server_id")
    private DutServer dutServer;
}
