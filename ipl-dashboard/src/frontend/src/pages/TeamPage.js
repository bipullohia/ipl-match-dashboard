import './TeamPage.scss';

import { React, useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { PieChart } from 'react-minimal-pie-chart';

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
      <div className="team-name-section">
        <h1 className="team-name">{team.teamName}</h1> 
      </div>
      <div className="win-loss-section">
        Wins/Losses
        <PieChart
          data={[
            { title: 'Losses', value: team.totalMatchCount - team.winCount, color: '#a34d5d' },
            { title: 'Wins', value: team.winCount, color: '#4da375' }
          ]}
        />
      </div>
      <div className="match-detail-section">
        <h3 className="latest-matches">Latest Matches</h3>
        <MatchDetailsCard teamName = {teamName} match = {team.matchesPlayed[0]}/>
      </div>
      {team.matchesPlayed.slice(1).map(match => <MatchSmallCard teamName = {teamName} match = {match} />
      )}
      <div className="more-link">
        <Link to={`/teams/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More ></Link>
      </div>
    </div>
  );
}