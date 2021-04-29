package com.bipullohia.ipldashboard.repository;

import com.bipullohia.ipldashboard.model.Team;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long>{
    
    public Team findByTeamName(String teamName);
}
