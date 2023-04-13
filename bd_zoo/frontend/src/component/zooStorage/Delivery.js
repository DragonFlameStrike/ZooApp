
const Delivery = (props) => {

    return (
        <div className="delivery">
            <h2>{props.delivery.foodType}: </h2>
            <p>Колличество - {props.delivery.count}</p>
            <p>Цена - {props.delivery.price}</p>
            <p>Дата - {props.delivery.date}</p>
        </div>
    );
};

export default Delivery;