import { React } from 'react';

export const MatchSmallCard = ({matchDetails}) => {
  return (
    <div className="MatchSmallCard">
      <p>{matchDetails.team1} vs {matchDetails.team2}</p>
    </div>
  );
}