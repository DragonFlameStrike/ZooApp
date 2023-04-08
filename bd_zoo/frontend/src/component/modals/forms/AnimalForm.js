import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Form, Label, Button } from 'reactstrap';

const AnimalForm = (props) => {

    const [animal, setAnimal] = useState([]);


    useEffect(() => {
        axios.get(`http://localhost:8080/animals/pool/${props.id}`)
            .then(response => {
                setAnimal(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const handleButtonClick = () => {
        axios.post(`http://localhost:8080/animals/pool/${props.id}`, {})
            .then(response => {
                props.toggle();
            })
            .catch(error => {
                console.log(error);
            });
        props.setNeedReload(!props.needReload)
    };

    return (
        <Form>
            {animal && (
                <>
                    <div className="mb-3">
                        <Label className="form-label">Вид:</Label>
                        {animal.type}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Кличка:</Label>
                        {animal.name}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Пол:</Label>
                        {animal.sex}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Вес:</Label>
                        {animal.weight}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Рост:</Label>
                        {animal.height}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Дата рождения:</Label>
                        {animal.birthday}
                    </div>
                    <Button color="primary" onClick={handleButtonClick}>
                        Добавить
                    </Button>
                </>
            )}
        </Form>
    );
}

export default AnimalForm;
