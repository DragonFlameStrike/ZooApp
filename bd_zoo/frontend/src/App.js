
import './App.css';
import Header from "./component/headers/Header";
import { BrowserRouter as Router} from 'react-router-dom';
import Sidebar from './component/sidebars/Sidebar';
import ZooCages from './component/zooCages/ZooCages';
import {useState} from "react";
import FlexModal from "./component/modals/FlexModal";



function App() {

    const [modalWorker, setModalWorker] = useState(false);
    const [modalAnimal, setModalAnimal] = useState(false);

    const toggleWorker = () => {
        setModalWorker(modalWorker => !modalWorker);
        console.log("workerModal")
    };
    const toggleAnimal = () => {
        setModalAnimal(modalAnimal => !modalAnimal);
        console.log("animalModal")
    };

    return (
        <Router>
            <div className="main_container">
                <nav>
                    <Header />
                </nav>
                <div className="main_div">
                    <main className="main">
                        <Sidebar toggleWorker={toggleWorker} toggleAnimal={toggleAnimal}/>
                        <ZooCages/>
                    </main>
                </div>
                <FlexModal modalWorker={modalWorker} modalAnimal={modalAnimal} toggleWorker={toggleWorker} toggleAnimal={toggleAnimal}/>
            </div>
        </Router>
    );
}


export default App;
