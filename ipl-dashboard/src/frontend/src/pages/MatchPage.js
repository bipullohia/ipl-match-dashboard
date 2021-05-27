import { React, useState, useEffect } from 'react';
import { useParams } from 'react-router';
import { MatchDetailsCard } from '../components/MatchDetailsCard';
import { YearSelector } from '../components/YearSelector';

import './MatchPage.scss';

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
    }, [teamName, year]
  );

  return (
    <div className="MatchPage">
      <div className="year-selector">
        <h4 className>Select the Year</h4>
        <YearSelector teamName = {teamName}/>
      </div>
      <div>
        <div className="match-page-heading">
        <h1>Showing matches for {teamName}</h1>
        <h3>{year}</h3>
        </div>
      {matches.map(match => <MatchDetailsCard teamName = {teamName} match = {match} />)}
      </div>
    </div>
  );
  }