import React, { useState, useEffect } from 'react';
import { useNavigate  } from 'react-router-dom';
import axios from 'axios';
import { Form, Label, Button, Input } from 'reactstrap';
import {useParams} from "react-router-dom";
import img from '../../photo/animals/default.png';
import leo from '../../photo/animals/Лев.png';
import tiger from '../../photo/animals/Тигр.png';
import whiteBear from '../../photo/animals/БелыйМедведь.png';
import wolf from '../../photo/animals/Волк.png';
import fox from '../../photo/animals/Лиса.png';
import straus from '../../photo/animals/Страус.png';
import parrot from '../../photo/animals/Попугай.png';
import blackBear from '../../photo/animals/БурыйМедведь.png';


const AnimalEditor  = (props) => {
    const [animal, setAnimal] = useState([]);
    const [cage, setCage] = useState([]);
    const [image, setImage] = useState(img);
    const {id} = useParams();
    const navigate = useNavigate();


    useEffect(() => {
        axios.get(`http://localhost:8080/animals/${id}`)
            .then(response => {
                setAnimal(response.data);
                choosePicture(response.data.type);
            })
            .catch(error => {
                console.log(error);
            });
        axios.get(`http://localhost:8080/animals/${id}/cage`)
            .then(response => {
                setCage(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const choosePicture = (type) => {
        if (type === "Лев") setImage(leo);
        if (type === "Тигр") setImage(tiger);
        if (type === "Белый Медведь") setImage(whiteBear);
        if (type === "Волк") setImage(wolf);
        if (type === "Лиса") setImage(fox);
        if (type === "Страус") setImage(straus);
        if (type === "Попугай") setImage(parrot);
        if (type === "Бурый Медведь") setImage(blackBear);
    }

    const handleButtonClick = () => {
        axios.put(`http://localhost:8080/animals/${id}`, {animal,cage} )
            .then(response => {
                navigate('/Zoo/animals/');
            })
            .catch(error => {
                console.log(error);
            });
    };


    return (
        <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', marginTop: '50px' }}>
            <img src={image} alt="animal photo" />
            <div style={{ maxWidth: '500px',  }}>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Вид:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} disabled={true} value={animal.type} onChange={(e) => setAnimal({...animal, type: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Кличка:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" value={animal.name} onChange={(e) => setAnimal({...animal, name: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Пол:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" disabled={true} value={animal.sex} onChange={(e) => setAnimal({...animal, sex: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Вес:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" value={animal.weight} onChange={(e) => setAnimal({...animal, weight: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Рост:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" value={animal.height} onChange={(e) => setAnimal({...animal, height: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Дата рождения:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text" value={animal.birthday} onChange={(e) => setAnimal({...animal, birthday: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Необходим теплый вальер?:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text"  disabled={true} value={animal.heatNeeded} onChange={(e) => setAnimal({...animal, heatNeeded: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Необходимость переселения?:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text"  disabled={true} value={animal.relocationNeeded} onChange={(e) => setAnimal({...animal, relocationNeeded: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Физическое состояние в норме?:</Label>
                    <input className="form-control" style={{ width: '400px', textAlign: 'right' }} type="text"  disabled={true} value={animal.physConditionNormally} onChange={(e) => setAnimal({...animal, physConditionNormally: e.target.value})} />
                </div>
                <div className="mb-3" style={{ display: 'flex', alignItems: 'center' }}>
                    <Label className="form-label" style={{ width: '150px' }}>Клетка:</Label>
                    <div style={{ display: 'flex', gap: '43px' }}>
                        {[1, 2, 3, 4, 5, 6].map((cell) => (
                            <div key={cell}>
                                <input
                                    type="radio"
                                    name="cage"
                                    value={cell.toString()}
                                    checked={cage.toString() === cell.toString()}
                                    onChange={(e) => setCage( e.target.value )}
                                />
                                {cell}
                            </div>
                        ))}
                    </div>
                </div>
                <Button color="primary" onClick={handleButtonClick}>
                    Сохранить
                </Button>
            </div>
        </div>

    );
}

export default  AnimalEditor;
