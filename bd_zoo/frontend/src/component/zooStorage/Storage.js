import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Food from './Food';

const Storage = (props) => {
    const PAGE_SIZE = 5; // установите значение PAGE_SIZE, если оно еще не было объявлено
    const [foods, setFoods] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        axios.get(`http://localhost:8080/Zoo/food/?page=${page}&size=${PAGE_SIZE}`)
            .then(response => {
                setFoods(response.data.content);
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
                foods.map(food => (
                    <Food key={food.id} food={food} toggle = {props.toggleFood} setFoodType={props.setFoodType} />
                )
            )}

            <div>
                {Array.from({length: totalPages}, (_, index) => index).map((pageNumber) => (
                    <button key={pageNumber} onClick={() => handlePageChange(pageNumber)}>
                        {pageNumber+1}
                    </button>
                ))}
            </div>
        </div>
    );
}

export default Storage;
