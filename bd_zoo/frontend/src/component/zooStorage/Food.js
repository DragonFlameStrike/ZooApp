import React from 'react';
import axios from "axios";

const Food = (props) => {

    const handleButtonClick = () => {
        props.setFoodType(props.food.type)
        props.toggle();
    };

    return (
        <div className="food-container">
            <h2>{props.food.type}</h2>
            <p>{props.food.count}</p>
            <button onClick={handleButtonClick}>Купить</button>
        </div>
    );
};

export default Food;