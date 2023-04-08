import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import Header from "./component/headers/Header";
import Editor from "./component/editor/Editor";
import Sidebar from './component/sidebars/Sidebar';
import ZooCages from './component/zooCages/ZooCages';
import {useState} from "react";
import FlexModal from "./component/modals/FlexModal";



function App() {

    const [modalWorker, setModalWorker] = useState(false);
    const [modalAnimal, setModalAnimal] = useState(false);
    const [needReload, setNeedReload] = useState(false);

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
                    <Header/>
                </nav>
                <Routes>
                    <Route path='/Zoo/*' element={
                        <div className="main_div">
                            <main className="main">
                                <Sidebar toggleWorker={toggleWorker} toggleAnimal={toggleAnimal} needReload={needReload}/>
                                <ZooCages/>
                            </main>
                        </div>}>
                    </Route>
                    <Route path="/Editor/*" element={
                        <Editor/>
                    }>
                    </Route>
                </Routes>
                <FlexModal
                    modalWorker={modalWorker}
                    modalAnimal={modalAnimal}
                    toggleWorker={toggleWorker}
                    toggleAnimal={toggleAnimal}
                    setNeedReload = {setNeedReload}
                    needReload={needReload}
                />
            </div>
        </Router>
    );
}


export default App;
