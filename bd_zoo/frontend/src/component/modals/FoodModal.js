import React from 'react';
import { Modal, ModalBody, ModalHeader } from 'reactstrap';
import FoodForm from './forms/FoodForm';

const FoodModal = (props) => {
    return (
        <Modal isOpen={props.modal} toggle={props.toggle} size="lg" style={{ maxWidth: '900px', width: '100%' }}>
            <ModalHeader tag="h4">{"Купить еду"}</ModalHeader>
            <ModalBody>
                <div className="row">
                    <div className="col-4">
                        <FoodForm id={0} toggle={props.toggle} setNeedReload={props.setNeedReload} needReload={props.needReload} foodType={props.foodType}/>
                    </div>
                    <div className="col-4">
                        <FoodForm id={1} toggle={props.toggle} setNeedReload={props.setNeedReload} needReload={props.needReload} foodType={props.foodType}/>
                    </div>
                    <div className="col-4">
                        <FoodForm id={2} toggle={props.toggle} setNeedReload={props.setNeedReload} needReload={props.needReload} foodType={props.foodType}/>
                    </div>
                </div>
            </ModalBody>
        </Modal>
    );
};

export default FoodModal;
