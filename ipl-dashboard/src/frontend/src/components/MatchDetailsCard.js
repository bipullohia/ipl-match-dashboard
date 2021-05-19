import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchDetailsCard = ({match, teamName}) => {
  
  //Possible area for providing more information about the empty values
  if(!match || !teamName) return null;
  
  const otherTeam = teamName === match.team1 ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;

  return (
    <div className="MatchDetailsCard">
      <h3>Latest Matches</h3>
      <h1>Vs <Link to={otherTeamRoute}>{otherTeam}</Link></h1>
      <h2>at {match.venue}</h2>
      <h3>{match.matchWinner} Won by {match.result} {match.resultMargin}</h3>
    </div>
  );
}