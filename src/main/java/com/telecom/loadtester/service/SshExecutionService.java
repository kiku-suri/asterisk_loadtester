package com.telecom.loadtester.service;

import com.jcraft.jsch.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class SshExecutionService {

    public String executeCommand(
            String host,
            Integer port,
            String username,
            String password,
            String command) {

        Session session = null;
        ChannelExec channel = null;

        try {

            JSch jsch = new JSch();

            session =
                    jsch.getSession(
                            username,
                            host,
                            port);

            session.setPassword(password);

            session.setConfig(
                    "StrictHostKeyChecking",
                    "no");

            session.connect(10000);

            channel =
                    (ChannelExec)
                            session.openChannel("exec");

            channel.setCommand(command);

            InputStream inputStream =
                    channel.getInputStream();

            channel.connect();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    inputStream));

            StringBuilder result =
                    new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {

                result.append(line)
                        .append("\n");
            }

            return result.toString();

        } catch (Exception ex) {

            throw new RuntimeException(
                    "SSH Execution Failed",
                    ex);

        } finally {

            if (channel != null) {
                channel.disconnect();
            }

            if (session != null) {
                session.disconnect();
            }
        }
    }

    public CommandResult executeWithResult(
        String host,
        Integer port,
        String username,
        String password,
        String command) {

    Session session = null;
    ChannelExec channel = null;

    try {

        JSch jsch = new JSch();

        session =
                jsch.getSession(
                        username,
                        host,
                        port);

        session.setPassword(password);

        session.setConfig(
                "StrictHostKeyChecking",
                "no");

        session.connect();

        channel =
                (ChannelExec)
                        session.openChannel("exec");

        channel.setCommand(command);

        InputStream input =
                channel.getInputStream();

        channel.connect();

        StringBuilder output =
                new StringBuilder();

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(input));

        String line;

        while ((line = reader.readLine()) != null) {

            output.append(line)
                    .append("\n");
        }

        while (!channel.isClosed()) {
            Thread.sleep(100);
        }

        return new CommandResult(
                channel.getExitStatus(),
                output.toString());

    } catch (Exception ex) {

        throw new RuntimeException(ex);

    } finally {

        if (channel != null)
            channel.disconnect();

        if (session != null)
            session.disconnect();
    }
  }
}
