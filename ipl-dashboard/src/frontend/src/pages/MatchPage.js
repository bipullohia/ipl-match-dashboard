import { React, useState, useEffect } from 'react';
import { useParams } from 'react-router';
import { MatchDetailsCard } from '../components/MatchDetailsCard';


export const MatchPage = () => {

  const [matches, setMatches] = useState([]);
  const { teamName, year } = useParams();

  useEffect(
    () => {
      const getMatches = async () => {
        const resp = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
        const matchData = await resp.json();
        setMatches(matchData);
      }
      getMatches();
    }, []
  );

  return (
    <div className="MatchPage">
      {matches.map(match => <MatchDetailsCard teamName = {teamName} match = {match} />)};
    </div>
  );
  }