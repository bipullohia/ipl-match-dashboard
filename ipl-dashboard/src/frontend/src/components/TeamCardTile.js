import { React } from 'react';
import { Link } from 'react-router-dom';

import './TeamCardTile.scss';

export const TeamCardTile = ({teamName}) => {

    return(
        <Link to={`/teams/${teamName}`}>
            <div className="TeamCardTile">
            <h1>{teamName}</h1>
            </div>
        </Link>
    );
}