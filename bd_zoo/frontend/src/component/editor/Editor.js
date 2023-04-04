
import React from 'react';
import { Routes ,Route } from 'react-router-dom';
import WorkerEditor from './WorkerEditor'
import AnimalEditor from './AnimalEditor'



const Editor = (props) => {
    return (
        <Routes>
            <Route path="/workers/*" element={<WorkerEditor/>} />
            <Route path="/animals/*" element={<AnimalEditor/>} />
            <Route path="/" />
        </Routes>

    );
}
export default Editor;