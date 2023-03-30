
import './App.css';
import Header from "./component/headers/Header";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Sidebar from './component/sidebars/Sidebar';
import ZooCages from './component/zooCages/ZooCages';



function App() {
    return (
        <Router>
            <div className="main_container">
                <nav>
                    <Header />
                </nav>
                <div className="main_div">
                    <main className="main">
                        <Sidebar/>
                        <ZooCages/>
                    </main>
                </div>
            </div>
        </Router>
    );
}


export default App;
