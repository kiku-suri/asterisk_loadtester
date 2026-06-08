package com.telecom.loadtester.service;

/*import com.telecom.loadtester.model.TestCampaign;
import com.telecom.loadtester.service.TestCampaignService;
import com.telecom.loadtester.service.SippExecutionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
*/

import com.telecom.loadtester.model.*;
import org.springframework.stereotype.Service;

@Service
public class SippExecutionService {

    private final SshExecutionService sshService;

    public SippExecutionService(
            SshExecutionService sshService) {

        this.sshService = sshService;
    }

    public String startCampaign(
            TestCampaign campaign) {

        LoadGenerator generator =
                campaign.getLoadGenerator();

	String scenario =
                "/opt/loadtester/scenarios/"
                        + campaign.getScenario()
                                  .getFileName();
        String csvFile =
                "/tmp/campaign_"
                        + campaign.getCampaignId()
                        + ".csv";

	String resultFile =
        "/tmp/campaign_"
                + campaign.getCampaignId()
                + "_stats.csv";
        
	campaign.setResultFile(resultFile);

        DutServer dut =
                campaign.getDutServer();

        TestConfiguration cfg =
                campaign.getTestConfiguration();

      /*  String command =
                String.format(
                        "nohup sipp %s " +
                        "-sn uac " +
                        "-r %d " +
                        "-l %d " +
                        "-m %d " +
                        "> /tmp/sipp.log 2>&1 & echo $!",
                        dut.getIpAddress(),
                        cfg.getCps(),
                        cfg.getCallCount(),
                        cfg.getCallCount());
*/
        String command =
                "nohup sipp "
                + campaign.getDutServer().getIpAddress()
                + " -sf " + scenario
                + " -r " + campaign.getCallsPerSecond()
                + " -m " + campaign.getTotalCalls()
                + " -trace_stat "
                + " -stf " + resultFile
                + " > /tmp/sipp.log 2>&1 & "
                + "echo $!";

        return sshService.executeCommand(
                generator.getIpAddress(),
                generator.getSshPort(),
                generator.getSshUsername(),
                generator.getSshPassword(),
                command).trim();
    }

/*    public String stopCampaign(
            TestCampaign campaign) {

        LoadGenerator generator =
                campaign.getLoadGenerator();

        return sshService.executeCommand(
                generator.getIpAddress(),
                generator.getSshPort(),
                generator.getSshUsername(),
                generator.getSshPassword(),
                "pkill -9 sipp");
    }
    */

           public void stopCampaign(
               TestCampaign campaign) {
       
           LoadGenerator generator =
                   campaign.getLoadGenerator();
       
           String cmd =
                   "kill -9 "
                   + campaign.getSippPid();
       
           sshService.executeCommand(
                   generator.getIpAddress(),
                   generator.getSshPort(),
                   generator.getSshUsername(),
                   generator.getSshPassword(),
                   cmd);
          }
     
          public boolean isRunning(
             TestCampaign campaign) {
     
         LoadGenerator generator =
                 campaign.getLoadGenerator();
     
         String cmd =
                 "ps -p "
                 + campaign.getSippPid();
     
         String result =
                 sshService.executeCommand(
                         generator.getIpAddress(),
                         generator.getSshPort(),
                         generator.getSshUsername(),
                         generator.getSshPassword(),
                         cmd);
     
         return result.contains(
                 campaign.getSippPid());
    }

	public String readStatisticsFile(
 	       TestCampaign campaign) {

    	LoadGenerator generator =
            campaign.getLoadGenerator();

    	return sshService.executeCommand(
            generator.getIpAddress(),
            generator.getSshPort(),
            generator.getSshUsername(),
            generator.getSshPassword(),
            "cat " + campaign.getResultFile());
	}


}
