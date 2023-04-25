import React, { useState, useEffect } from 'react';
import axios from "axios";
import Feed from "./Feed";

const Kitchen = (props) => {
    const [feeds, setFeeds] = useState([]);

    useEffect(() => {
        axios.get(`http://localhost:8080/feeds/`)
            .then(response => {
                setFeeds(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    const handleButtonClick = () => {
        props.toggle();
    };

    return (
        <div>
            <div>
                <button onClick={handleButtonClick}>Создать блюдо</button>
            </div>
            <div>
                {
                    feeds.map(feed => (
                            <Feed key={feed.id}
                                  feed={feed}
                            />
                        )
                    )}
            </div>
        </div>
    );
}
export default Kitchen;