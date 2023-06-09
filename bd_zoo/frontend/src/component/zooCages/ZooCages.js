import { useState, useEffect } from 'react';
import axios from 'axios';
import Cage from "./Cage";

const ZooCages = (props) => {
    const [cages, setCages] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/cages/')
            .then(response => {
                setCages(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const cageNumbers = cages.map((cage) => cage.number);

    const sections = Array.from({ length: 6 }).map((_, index) => (
        <div className="section" key={index}>
            {cageNumbers[index] ? (
                <Cage index={cageNumbers[index]}/>
            ) : (
                <button onClick={() => handleBuyClick(index)}>Купить клетку</button>
            )}
        </div>
    ));

    const handleBuyClick = (index) => {
        axios.post('http://localhost:8080/cages/', {
            id:null,
            number: index + 1
        })
            .then(response => {
                setCages([...cages, response.data]);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    return(
        <section className="content">
            {sections}
        </section>
    );
};

export default ZooCages;
