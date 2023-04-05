
import React from 'react';
import { Routes ,Route } from 'react-router-dom';
import WorkerEditor from './WorkerEditor'
import AnimalEditor from './AnimalEditor'



const Editor = (props) => {
    return (
        <Routes>
            <Route path="/workers/:id" element={<WorkerEditor/>} />
            <Route path="/animals/:id" element={<AnimalEditor/>} />
            <Route path="/" />
        </Routes>

    );
}
export default Editor;