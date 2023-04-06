import axios from 'axios';
import { useState, useEffect } from 'react';

const AnimalSidebar  = (props) => {
    const [animals, setAnimals] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/animals/')
            .then(response => {
                setAnimals(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    return (
        <aside className="sidebar">
            <header className="sidebar__header">Животные:</header>
            <section className="sidebar__content">
                <div className="sidebar__menu">
                    {animals.map(animal => (
                        <a href={"http://localhost:3000/Editor/animals/" + animal.id} className="sidebar__menu-item" key={animal.id}>{animal.type + " " + animal.name}</a>
                    ))}
                </div>
            </section>
            <footer className="sidebar__footer">
                <button className="sidebar__submit-button" onClick={props.toggle}>Купить животное</button>
            </footer>
        </aside>
    );
}

export default  AnimalSidebar;
