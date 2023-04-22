import React, { useState, useEffect } from 'react';
import { Modal, ModalBody, ModalHeader } from 'reactstrap';
import axios from 'axios';
import {Form, FormGroup, Label, Input, Button} from 'reactstrap';

const VaccinationForm = (props) => {

    const [vaccinations, setVaccinations] = useState([]);
    const [selectedVaccinations, setSelectedVaccinations] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/vaccination/`)
            .then(response => {
                setVaccinations(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const handleCheckboxChange = (e) => {
        const vaccine = e.target.value;
        const isChecked = e.target.checked;

        if (isChecked) {
            setSelectedVaccinations([...selectedVaccinations, vaccine]);
        } else {
            setSelectedVaccinations(selectedVaccinations.filter(type => type !== vaccine));
        }
    };

    const handleButtonClick = () => {
        axios.post(`http://localhost:8080/vaccination/${props.animalToVaccination}`, {vaccinations: selectedVaccinations})
            .then(response => {
                props.toggle();
            })
            .catch(error => {
                console.log(error);
            });
        props.setNeedReload(!props.needReload)
    };

    return (
        <Modal isOpen={props.modal} toggle={props.toggle} size="lg" style={{maxWidth: '900px', width: '100%'}}>
            <ModalHeader tag="h4">{"Вакцины"}</ModalHeader>
            <ModalBody>
                <Form>
                    <FormGroup>
                        {vaccinations.map(vaccine => (
                            <Label check key={vaccine.id} style={{ display: 'block' }}>
                                <Input type="checkbox" value={vaccine} onChange={handleCheckboxChange} />{' '}
                                {vaccine}
                            </Label>
                        ))}
                    </FormGroup>
                    <Button color="primary" onClick={handleButtonClick}>
                        Вакцинировать
                    </Button>
                </Form>
            </ModalBody>
        </Modal>
    );
}

export default VaccinationForm;
