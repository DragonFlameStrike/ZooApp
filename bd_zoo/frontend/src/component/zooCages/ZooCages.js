import { useState, useEffect } from 'react';
import axios from 'axios';

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
                <p>Cage {cageNumbers[index]}</p>
            ) : (
                <button onClick={() => handleBuyClick(index)}>Buy</button>
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
