import { React, useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';


export const TeamPage = () => {

  const [team, setTeam] = useState({matchesPlayed:[]});
  const { teamName } = useParams();

  useEffect(
    () => {
      
      const getMatches = async () => {
        const resp = await fetch(`http://localhost:8080/team/${teamName}`);
        const matchData = await resp.json();
        setTeam(matchData);
      }
      getMatches();
    }, [teamName]
  );
  
  if(!team || !team.teamName){
    return(
      <h1>No data found for the team: {teamName}!</h1>
    );
  }

  return (
    <div className="TeamPage">
      <h1>{team.teamName}</h1>
      <MatchDetailsCard teamName = {teamName} match = {team.matchesPlayed[0]}/>
      {team.matchesPlayed.slice(1).map(match => <MatchSmallCard teamName = {teamName} match = {match} />
      )}
    </div>
  );
}