package com.bipullohia.ipldashboard.data;

import java.time.LocalDate;

import com.bipullohia.ipldashboard.model.Match;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class MatchItemProcessor implements ItemProcessor<MatchInput, Match>{
  
  //private static final Logger log = LoggerFactory.getLogger(MatchItemProcessor.class);

  @Override
  public Match process(final MatchInput matchInput) throws Exception {
    
    Match matchOutput = new Match();

    matchOutput.setId(Long.parseLong(matchInput.getId()));
    matchOutput.setCity(matchInput.getCity());
    matchOutput.setDate(LocalDate.parse(matchInput.getDate()));
    matchOutput.setPlayerOfMatch(matchInput.getPlayer_of_match());
    matchOutput.setVenue(matchInput.getVenue());

    //Setting Team batting first as team1 and the one batting second as team2
    String firstInningsTeam = "";
    String secondInningsTeam = "";
    if("bat".equalsIgnoreCase(matchInput.getToss_decision())){
        firstInningsTeam = matchInput.getToss_winner();
        secondInningsTeam = matchInput.getTeam1().equals(matchInput.getToss_winner()) 
            ? matchInput.getTeam2(): matchInput.getTeam1();

    }else{
        secondInningsTeam = matchInput.getToss_winner();
        firstInningsTeam = matchInput.getTeam1().equals(matchInput.getToss_winner()) 
            ? matchInput.getTeam2(): matchInput.getTeam1();
    }

    matchOutput.setTeam1(firstInningsTeam);
    matchOutput.setTeam2(secondInningsTeam);

    matchOutput.setTossWinner(matchInput.getToss_winner());
    matchOutput.setTossDecision(matchInput.getToss_decision());
    matchOutput.setMatchWinner(matchInput.getWinner());
    matchOutput.setResult(matchInput.getResult());
    matchOutput.setResultMargin(matchInput.getResult_margin());
    matchOutput.setUmpire1(matchInput.getUmpire1());
    matchOutput.setUmpire2(matchInput.getUmpire2());

    return matchOutput;
  }
}
