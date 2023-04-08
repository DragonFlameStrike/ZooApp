
import React from 'react';
import { Routes ,Route } from 'react-router-dom';
import WorkerSidebar from './WorkerSidebar';
import AnimalSidebar from './AnimalSidebar';


const Sidebar = (props) => {
    return (
        <Routes>
            <Route path="/workers/*" element={<WorkerSidebar toggle={props.toggleWorker} needReload={props.needReload}/>} />
            <Route path="/animals/*" element={<AnimalSidebar toggle={props.toggleAnimal} needReload={props.needReload}/>} />
            <Route path="/" />
        </Routes>

    );
}
export default Sidebar;