import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Form, Row, Col, Label, Button } from 'reactstrap';

const WorkerForm = (props) => {

    const [worker, setWorker] = useState([]);


    useEffect(() => {
        axios.get('http://localhost:8080/workers/pool/' + props.id)
            .then(response => {
                setWorker(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const handleButtonClick = () => {
        axios.post(`http://localhost:8080/workers/pool/${props.id}`, {})
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
            {worker && (
                <>
                    <div className="mb-3">
                        <Label className="form-label">Имя:</Label>
                        {worker.name}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Пол:</Label>
                        {worker.sex}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Профессия:</Label>
                        {worker.profession}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Дата рождения:</Label>
                        {worker.birthday}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Стаж:</Label>
                        {worker.priorService}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Зарплата:</Label>
                        {worker.salary}
                    </div>
                    <Button color="primary" onClick={handleButtonClick}>Нанять</Button>
                </>
            )}
        </Form>
    );
}

export default WorkerForm;
