
import React from 'react';
import { Routes ,Route } from 'react-router-dom';
import WorkerSidebar from './WorkerSidebar';
import AnimalSidebar from './AnimalSidebar';


const Sidebar = (props) => {
    return (
        <Routes>
            <Route path="/workers/*" element={<WorkerSidebar toggle={props.toggleWorker} />} />
            <Route path="/animals/*" element={<AnimalSidebar toggle={props.toggleAnimal} />} />
            <Route path="/" />
        </Routes>

    );
}
export default Sidebar;