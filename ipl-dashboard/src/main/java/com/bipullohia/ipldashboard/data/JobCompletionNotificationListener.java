package com.bipullohia.ipldashboard.data;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.bipullohia.ipldashboard.model.Team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager em;

  @Autowired
  public JobCompletionNotificationListener(EntityManager em) {
    this.em = em;
  }

  Map<String, Team> teamData = new HashMap<>();

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("Batch job Finished!");
    }

    //create a new team instance in the map for each individual team from the column team 1
    em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
      .getResultList()
      .stream()
      .map(res-> new Team((String) res[0], (long) res[1]))
      .forEach(team-> teamData.put(team.getTeamName(), team));

    //using column team2, we again add the count of matches to the teams already created above (ignore the case where the team has already played as team2)  
    em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
      .getResultList()
      .stream()
      .forEach(res-> {
        Team team = teamData.get((String)res[0]);
        team.setTotalMatchCount(team.getTotalMatchCount() + (long) res[1]);
      });

    //setting total wins of the individual teams  
    em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
      .getResultList()
      .stream()
      .forEach(res-> {
        Team team = teamData.get((String) res[0]);
        if(team != null){
          team.setWinCount((long) res[1]);
        }
      });

    //persist the data to the DB
    teamData.values().forEach(team -> em.persist(team));

    teamData.values().forEach(team -> System.out.println(team.toString()));
  }
}
