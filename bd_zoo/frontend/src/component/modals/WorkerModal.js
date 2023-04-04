import React from 'react';
import {Modal, ModalBody, ModalHeader} from "reactstrap";
import WorkerForm from "./forms/WorkerForm";


const WorkerModal = (props) => {
    return (
        <Modal isOpen={props.modal} toggle={props.toggle}  size="lg" style={{maxWidth: '900px', width: '100%'}} >
            <ModalHeader tag="h4" >
                {"Нанять нового работника"}
            </ModalHeader>
            <ModalBody>
                <div className="row">
                    <div className="col-4">
                        <WorkerForm id={0} toggle={props.toggle} />
                    </div>
                    <div className="col-4">
                        <WorkerForm id={1} toggle={props.toggle} />
                    </div>
                    <div className="col-4">
                        <WorkerForm id={2} toggle={props.toggle} />
                    </div>
                </div>
            </ModalBody>
        </Modal>

    );
}
export default WorkerModal;