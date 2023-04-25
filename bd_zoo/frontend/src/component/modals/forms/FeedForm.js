import React, { useState, useEffect } from 'react';
import { Modal, ModalBody, ModalHeader } from 'reactstrap';
import axios from 'axios';
import {Form, FormGroup, Label, Input, Button} from 'reactstrap';

const FeedForm = (props) => {

    const [foods, setFoods] = useState([]);
    const [animals, setAnimals] = useState([])
    const [feedingPerWeek, setFeedingPerWeek] = useState([])
    const [selectedAnimal, setSelectedAnimal] = useState(null);
    const [selectedFoods, setSelectedFoods] = useState([]);
    const [season, setSeason] = useState('');

    useEffect(() => {
        axios.get(`http://localhost:8080/food/all`)
            .then(response => {
                setFoods(response.data);
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
    }, []);

    const handleAnimalChange = (e) => {
        setSelectedAnimal(e.target.value);
    };

    const handleCheckboxChange = (e) => {
        const foodId = e.target.value;
        const isChecked = e.target.checked;

        if (isChecked) {
            setSelectedFoods([...selectedFoods, foodId]);
        } else {
            setSelectedFoods(selectedFoods.filter(id => id !== foodId));
        }
    };

    const handleButtonClick = () => {
        axios.post(`http://localhost:8080/feeds/`, {foodsId: selectedFoods,animalId : selectedAnimal,feedingPerWeek : feedingPerWeek,season: season})
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
            <ModalHeader tag="h4">{"Создание блюда"}</ModalHeader>
            <ModalBody>
                <Form>
                    <FormGroup style={{ columnCount: 3 }}>
                        {foods.map(food => (
                            <Label check key={food.id} style={{ display: 'block' }}>
                                <Input type="checkbox" value={food.id} onChange={handleCheckboxChange} />{' '}
                                {food.type}
                            </Label>
                        ))}
                    </FormGroup>
                    <FormGroup>
                        <Label for="animal-select">Животное</Label>
                        <Input type="select" name="select" id="animal-select" onChange={(e) => handleAnimalChange(e)}>
                            {animals.map(animal => (
                                <option key={animal.id} value={animal.id}>{animal.name + " " + animal.type}</option>
                            ))}
                        </Input>
                    </FormGroup>
                    <Input type="number" value={feedingPerWeek} onChange={(e) => setFeedingPerWeek(parseInt(e.target.value))} />
                    <FormGroup>
                        <Label for="season">Сезон:</Label>
                        <div>
                            <Label check>
                                <Input type="radio" name="season" value="Summer" checked={season === 'Summer'} onChange={() => setSeason('Summer')} />{' '}
                                Summer
                            </Label>
                        </div>
                        <div>
                            <Label check>
                                <Input type="radio" name="season" value="Winter" checked={season === 'Winter'} onChange={() => setSeason('Winter')} />{' '}
                                Winter
                            </Label>
                        </div>
                    </FormGroup>
                    <Button color="primary" onClick={handleButtonClick}>
                        Создать
                    </Button>
                </Form>
            </ModalBody>
        </Modal>
    );
}

export default FeedForm;
