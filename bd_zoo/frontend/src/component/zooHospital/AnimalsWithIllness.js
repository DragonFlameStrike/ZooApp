import React from 'react';


const animalsWithIllness = (props) => {

    const handleButtonClick = () => {
        props.setAnimalToVaccination(props.animal.id);
        props.toggle();
    };

    return (
        <div className="food-container">
            <h2>{props.animal.name}</h2>
            {props.illnesses.map((illness) => (
                <p key={illness.id}>{illness.type}</p>
            ))}
            <button onClick={handleButtonClick}>Вакцинировать</button>
        </div>
    );
};

export default animalsWithIllness;