package com.bipullohia.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import com.bipullohia.ipldashboard.model.Match;
import com.bipullohia.ipldashboard.model.Team;
import com.bipullohia.ipldashboard.repository.MatchRepository;
import com.bipullohia.ipldashboard.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class TeamController {
    
    @Autowired
    private TeamRepository teamRepo;

    @Autowired
    private MatchRepository matchRepo;

    /*
    Instead of the Autowired functionality, we can use:
    
    private TeamRepository teamRepo;

    public TeamController(TeamRepository teamRepo){
        this.teamRepo=teamRepo;
    }

    In the function use - this.teamRepo.findByTeamName(teamName);
    */

    @GetMapping(value="teams")
    public Iterable<Team> getAllTeams(){
        return teamRepo.findAll();
    }

    @GetMapping(value="team/{teamName}")
    public Team getTeamDetails(@PathVariable String teamName){
        Team team = teamRepo.findByTeamName(teamName);        
        team.setMatchesPlayed(matchRepo.getLatestMatchesByTeam(teamName, 4));
        return team;
    }

    @GetMapping(value = "team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year+1, 1, 1);
        return matchRepo.getMatchesForTeamByDates(teamName,  startDate, endDate);
    }
}
