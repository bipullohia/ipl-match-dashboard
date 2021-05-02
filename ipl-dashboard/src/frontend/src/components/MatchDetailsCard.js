import { React } from 'react';

export const MatchDetailsCard = ({match}) => {
  if(!match) return null;
  return (
    <div className="MatchDetailsCard">
      <h3>Match Details</h3>
      <h4>{match.team1} Vs {match.team2}</h4>
    </div>
  );
}