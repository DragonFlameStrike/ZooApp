import React, { useState, useEffect } from 'react';
import axios from "axios";
import AnimalsWithIllness from "./AnimalsWithIllness";

const Hospital = (props) => {
    const PAGE_SIZE = 4;
    const [animalsWithIllness, setAnimalsWithIllness] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        axios.get(`http://localhost:8080/animals/illness?page=${page}&size=${PAGE_SIZE}`)
            .then(response => {
                setAnimalsWithIllness(response.data.content);
                setTotalPages(response.data.totalPages);
            })
            .catch(error => {
                console.log(error);
            });
    }, [page]);

    const handlePageChange = (newPage) => {
        setPage(newPage);
    }

    return (
        <div>
            {
                animalsWithIllness.map(animalWithIllness => (
                        <AnimalsWithIllness key={animalWithIllness.id}
                                            illnesses={animalWithIllness.illnesses}
                                            animal={animalWithIllness.animal}
                                            toggle={props.toggle}
                                            setAnimalToVaccination={props.setAnimalToVaccination}
                        />
                    )
                )}
        </div>
    );
}
export default Hospital;