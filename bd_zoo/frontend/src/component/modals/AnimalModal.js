import React from 'react';
import { Modal, ModalBody, ModalHeader } from 'reactstrap';
import AnimalForm from './forms/AnimalForm';

const AnimalModal = (props) => {
    return (
        <Modal isOpen={props.modal} toggle={props.toggle} size="lg" style={{ maxWidth: '900px', width: '100%' }}>
            <ModalHeader tag="h4">{"Добавить животное"}</ModalHeader>
            <ModalBody>
                <div className="row">
                    <div className="col-4">
                        <AnimalForm id={0} toggle={props.toggle} setNeedReload={props.setNeedReload} needReload={props.needReload} />
                    </div>
                    <div className="col-4">
                        <AnimalForm id={1} toggle={props.toggle} setNeedReload={props.setNeedReload} needReload={props.needReload}/>
                    </div>
                    <div className="col-4">
                        <AnimalForm id={2} toggle={props.toggle} setNeedReload={props.setNeedReload} needReload={props.needReload}/>
                    </div>
                </div>
            </ModalBody>
        </Modal>
    );
};

export default AnimalModal;
