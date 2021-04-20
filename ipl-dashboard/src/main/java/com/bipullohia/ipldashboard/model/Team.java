package com.bipullohia.ipldashboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long winCount;
    private long totalMatchCount;

    public Team(String teamName, long totalMatchCount) {
        this.teamName = teamName;
        this.totalMatchCount = totalMatchCount;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public long getWinCount() {
        return winCount;
    }
    public void setWinCount(long winCount) {
        this.winCount = winCount;
    }
    public long getTotalMatchCount() {
        return totalMatchCount;
    }
    public void setTotalMatchCount(long totalMatchCount) {
        this.totalMatchCount = totalMatchCount;
    }
    @Override
    public String toString() {
        return "Team [teamName=" + teamName + ", totalMatchCount=" + totalMatchCount + ", winCount=" + winCount + "]";
    }
}
