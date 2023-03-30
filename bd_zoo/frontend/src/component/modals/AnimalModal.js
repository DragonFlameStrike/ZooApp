import React from 'react';
import {Modal, ModalBody, ModalHeader} from "reactstrap";


const AnimalModal = (props) => {
    return (
        <Modal isOpen={props.modal} toggle={props.toggle}>
            <ModalHeader tag="h4">
                {"Купить новое животное"}
            </ModalHeader>
            <ModalBody>

            </ModalBody>
        </Modal>

    );
}
export default AnimalModal;