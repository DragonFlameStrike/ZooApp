import React from 'react';
import { Routes ,Route } from 'react-router-dom';

import WorkerModal from "./WorkerModal";
import AnimalModal from "./AnimalModal";


const FlexModal = (props) => {
    return (
        <Routes>
            <Route path="/Zoo/workers/" element={<WorkerModal toggle={props.toggleWorker} modal={props.modalWorker} />} />
            <Route path="/Zoo/animals/" element={<AnimalModal toggle={props.toggleAnimal} modal={props.modalAnimal} />} />
            <Route path="/" />
        </Routes>

    );
}
export default FlexModal;