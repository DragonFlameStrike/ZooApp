import axios from 'axios';
import { useState, useEffect } from 'react';

const WorkerSidebar  = (props) => {
    const [workers, setWorkers] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/workers/')
            .then(response => {
                setWorkers(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }, [props.needReload]);

    return (
        <aside className="sidebar">
            <header className="sidebar__header">Рабочие:</header>
            <section className="sidebar__content">
                <div className="sidebar__menu">
                    {workers.map(worker => (
                        <a href={"http://localhost:3000/Editor/workers/" + worker.id} className="sidebar__menu-item" key={worker.id}>{worker.name}</a>
                    ))}
                </div>
            </section>
            <footer className="sidebar__footer">
                <button className="sidebar__submit-button" onClick={props.toggle}>Нанять рабочего</button>
            </footer>
        </aside>
    );
}

export default  WorkerSidebar;
