import React, { useState, useEffect } from 'react';
import axios from "axios";
import Delivery from "./Delivery";


const StorageHistory = (props) => {
    const PAGE_SIZE = 4; // установите значение PAGE_SIZE, если оно еще не было объявлено
    const [deliveries, setDeliveries] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);

    useEffect(() => {
        axios.get(`http://localhost:8080/deliveries/?page=${page}&size=${PAGE_SIZE}`)
            .then(response => {
                setDeliveries(response.data.content);
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
                deliveries.map(delivery => (
                        <Delivery key={delivery.id} delivery={delivery} />
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

export default StorageHistory;
