import React, { useState, useEffect } from 'react';
import { useNavigate  } from 'react-router-dom';
import axios from 'axios';
import { Form, Label, Button, Input } from 'reactstrap';
import {useParams} from "react-router-dom";
import img from "../../photo/animals/default.png";
import leo from "../../photo/animals/Лев.png";
import tiger from "../../photo/animals/Тигр.png";
import whiteBear from "../../photo/animals/БелыйМедведь.png";
import wolf from "../../photo/animals/Волк.png";
import fox from "../../photo/animals/Лиса.png";


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
                choosePicture(response.data.type);
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
        if (type === "Ветеринар") setImage(leo);
        if (type === "Кормильщик") setImage(tiger);
        if (type === "Уборщик") setImage(whiteBear);
        if (type === "Надзиратель") setImage(wolf);
        if (type === "Разнорабочий") setImage(fox);
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
