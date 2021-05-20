package com.bipullohia.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import com.bipullohia.ipldashboard.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends CrudRepository<Match, Long> {
    
    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    //Longer, Ugly way of writing the query as function name
    // List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    //     String teamName1, LocalDate startDate1, LocalDate endDate1, String teamName2, LocalDate startDate2, LocalDate endDate2);

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) and (m.date between :startDate and :endDate) order by date desc")
    List<Match> getMatchesForTeamByDates(@Param("teamName") String teamName,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

    default List<Match> getLatestMatchesByTeam(String teamName, int count){
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
