
import React from 'react';
import { Routes ,Route } from 'react-router-dom';
import WorkerSidebar from './WorkerSidebar';
import AnimalSidebar from './AnimalSidebar';


const Sidebar = (props) => {
    return (
        <Routes>
            <Route path="/Zoo/workers/" element={<WorkerSidebar toggle={props.toggleWorker} />} />
            <Route path="/Zoo/animals/" element={<AnimalSidebar toggle={props.toggleAnimal} />} />
            <Route path="/" />
        </Routes>

    );
}
export default Sidebar;