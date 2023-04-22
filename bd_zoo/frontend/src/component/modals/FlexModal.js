import React from 'react';
import { Routes ,Route } from 'react-router-dom';

import WorkerModal from "./WorkerModal";
import AnimalModal from "./AnimalModal";
import FoodModal from "./FoodModal";
import VaccinationForm from "./forms/VaccinationForm";


const FlexModal = (props) => {
    return (
        <Routes>
            <Route path="/Zoo/workers/" element=
                {<WorkerModal toggle={props.toggleWorker} modal={props.modalWorker}  setNeedReload={props.setNeedReload}  needReload={props.needReload}/>} />
            <Route path="/Zoo/animals/" element=
                {<AnimalModal toggle={props.toggleAnimal} modal={props.modalAnimal} setNeedReload={props.setNeedReload}  needReload={props.needReload}/>} />
            <Route path="/Zoo/storage/" element=
                {<FoodModal toggle={props.toggleFood} modal={props.modalFood} foodType={props.foodType} setNeedReload={props.setNeedReload}  needReload={props.needReload}/>} />
            <Route path="/Zoo/hospital/" element=
                {<VaccinationForm toggle={props.toggleVaccination} modal={props.modalVaccination} animalToVaccination={props.animalToVaccination} setNeedReload={props.setNeedReload}  needReload={props.needReload}/>} />
            <Route path="/" />
        </Routes>

    );
}
export default FlexModal;