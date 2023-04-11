import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Form, Label, Button } from 'reactstrap';

const FoodForm = (props) => {

    const [supplier, setSupplier] = useState([]);


    useEffect(() => {
        axios.get(`http://localhost:8080/suppliers/${props.foodType}/${props.id}`)
            .then(response => {
                setSupplier(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const handleButtonClick = () => {
        axios.post(`http://localhost:8080/Zoo/food/${props.foodType}/add/${supplier.count}`, {})
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
            {supplier && (
                <>
                    <div className="mb-3">
                        <Label className="form-label">Тип еды:</Label>
                        {supplier.type}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Колличество:</Label>
                        {supplier.count}
                    </div>
                    <div className="mb-3">
                        <Label className="form-label">Цена:</Label>
                        {supplier.price}
                    </div>
                    <Button color="primary" onClick={handleButtonClick}>
                        Купить
                    </Button>
                </>
            )}
        </Form>
    );
}

export default FoodForm;
