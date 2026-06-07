package com.telecom.loadtester.service;

import com.jcraft.jsch.*;
import com.telecom.loadtester.model.LoadGenerator;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class ScenarioDeploymentService {

    public void deployScenario(
            String localFile,
            LoadGenerator generator) throws Exception {

        JSch jsch = new JSch();

        Session session =
                jsch.getSession(
                        generator.getSshUsername(),
                        generator.getIpAddress(),
                        generator.getSshPort());

        session.setPassword(
                generator.getSshPassword());

        session.setConfig(
                "StrictHostKeyChecking",
                "no");

        session.connect();

        Channel channel =
                session.openChannel("sftp");

        channel.connect();

        ChannelSftp sftp =
                (ChannelSftp) channel;

        sftp.cd(
                "/opt/loadtester/scenarios");

        sftp.put(
                new FileInputStream(localFile),
                localFile.substring(
                        localFile.lastIndexOf("/") + 1));

        sftp.exit();
        session.disconnect();
    }
}
