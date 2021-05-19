import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchSmallCard = ({match, teamName}) => {
  const otherTeam = teamName === match.team1 ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  
  return (
    <div className="MatchSmallCard">
      <h4>Vs <Link to={otherTeamRoute}>{otherTeam}</Link></h4>
      <p>{match.matchWinner} Won by {match.result} {match.resultMargin}</p>
    </div>
  );
}