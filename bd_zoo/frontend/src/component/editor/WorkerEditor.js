import React, { useState, useEffect } from 'react';
import { useNavigate  } from 'react-router-dom';
import axios from 'axios';
import { Form, Label, Button, Input } from 'reactstrap';
import {useParams} from "react-router-dom";
import img from "../../photo/animals/default.png";
import wet from "../../photo/workers/Ветеринар.png";
import cook from "../../photo/workers/Кормильщик.png";
import cleaner from "../../photo/workers/Уборщик.png";
import looker from "../../photo/workers/Надзиратель.png";
import superman from "../../photo/workers/Разнорабочий.png";


const WorkerEditor  = (props) => {
    const [worker, setWorker] = useState([]);
    const [animals, setAnimals] = useState([]);
    const [currAnimalsId, setCurrAnimalsId] = useState([]);

    const [image, setImage] = useState(img);
    const {id} = useParams();
    const navigate = useNavigate();


    useEffect(() => {
        axios.get(`http://localhost:8080/workers/${id}`)
            .then(response => {
                setWorker(response.data);
                choosePicture(response.data.profession);
            })
            .catch(error => {
                console.log(error);
            });
        axios.get(`http://localhost:8080/animals/`)
            .then(response => {
                setAnimals(response.data);
            })
            .catch(error => {
                console.log(error);
            });
        axios.get(`http://localhost:8080/workers/${id}/animals`)
            .then(response => {
                setCurrAnimalsId(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const choosePicture = (type) => {
        if (type === "Ветеринар") setImage(wet);
        if (type === "Кормильщик") setImage(cook);
        if (type === "Уборщик") setImage(cleaner);
        if (type === "Надзиратель") setImage(looker);
        if (type === "Разнорабочий") setImage(superman);
    }

    const handleButtonClick = () => {
        axios.put(`http://localhost:8080/workers/${id}`, {worker,currAnimalsId} )
            .then(response => {
                navigate('/Zoo/workers/');
            })
            .catch(error => {
                console.log(error);
            });
    };
    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '50px' }}>
            <img src={image} alt="worker photo" />
            <div style={{ maxWidth: '500px',  }}>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Профессия:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} disabled={true} value={worker.profession} onChange={(e) => setWorker({...worker, type: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Имя:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" value={worker.name} onChange={(e) => setWorker({...worker, name: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Пол:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" disabled={true} value={worker.sex} onChange={(e) => setWorker({...worker, sex: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Дата найма:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" value={worker.hireDate} onChange={(e) => setWorker({...worker, hireDate: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Опыт работы:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" value={worker.priorService} onChange={(e) => setWorker({...worker, priorService: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Дата рождения:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" value={worker.birthday} onChange={(e) => setWorker({...worker, birthday: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Зарплата:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text"  value={worker.salary} onChange={(e) => setWorker({...worker, salary: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Ответственен за таких животных, как:</Label>
                    <select multiple className="form-control" style={{ width: '400px' }} value={currAnimalsId} onChange={(e) => setCurrAnimalsId(Array.from(e.target.selectedOptions, option => option.value))}>
                        {animals.map(animal => (
                            <option key={animal.id} value={animal.id}>{animal.name}</option>
                        ))}
                    </select>
                </div>

                <Button color="primary" onClick={handleButtonClick}>
                    Сохранить
                </Button>
            </div>
        </div>

    );
}

export default  WorkerEditor;
