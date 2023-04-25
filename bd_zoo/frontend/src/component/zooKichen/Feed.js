import React from 'react';


const Feed = (props) => {

    const handleButtonClick = () => {

    };

    return (
        <div className="food-container">
            <h2>{props.feed.animalName}</h2>
            <p>Сезон: {props.feed.feed.season}</p>
            <p> Кол-во кормлений в неделю: {props.feed.feed.feedingPerWeek}</p>
            {props.feed.foodTypes.map((type) => (
                <p key={type.id}>{type}</p>
            ))}
            <button onClick={handleButtonClick}>Удалить</button>
        </div>
    );
};

export default Feed;