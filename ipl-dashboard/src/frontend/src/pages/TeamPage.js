import { React, useState, useEffect } from 'react';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';


export const TeamPage = () => {

  const [team, setTeam] = useState({matchesPlayed:[]});

  useEffect(
    () => {
      
      const getMatches = async () => {
        const resp = await fetch('http://localhost:8080/team/Delhi%20Capitals');
        const matchData = await resp.json();
        setTeam(matchData);
      }
      getMatches();
    }, []
  );


  return (
    <div className="TeamPage">
      <h1>{team.teamName}</h1>
      <MatchDetailsCard match = {team.matchesPlayed[0]}/>
      {team.matchesPlayed.slice(1).map(match => <MatchSmallCard matchDetails = {match} />
      )}
    </div>
  );
}