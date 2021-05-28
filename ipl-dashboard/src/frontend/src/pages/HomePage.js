import { React, useState, useEffect } from 'react';
import { TeamCardTile } from '../components/TeamCardTile';

import './HomePage.scss';

export const HomePage = () => {

    const [teams, setTeams] = useState([]);

    useEffect(
        () => {
            const getAllTeams = async () => {
                const resp = await fetch(`http://localhost:8080/teams/`); 
                const allTeams = await resp.json();
                setTeams(allTeams);
            };
            getAllTeams();
        }, []
    );

    return(
        <div className="HomePage">
            <h1 className="home-page-header">IPL Dashboard</h1>
            <p className="home-page-subheader">Select a team to get started</p>
            <div className="teams-grid">
                {teams.map(team => <TeamCardTile teamName={team.teamName}/>)}
            </div>
        </div>
    );
}