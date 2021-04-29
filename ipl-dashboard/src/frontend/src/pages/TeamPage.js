import { React } from 'react';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

export const TeamPage = () => {
  return (
    <div className="TeamPage">
      <h1>Team name</h1>
      <MatchDetailsCard />
      <MatchSmallCard />
      <MatchSmallCard />
      <MatchSmallCard />
    </div>
  );
}