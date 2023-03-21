
import './App.css';
import Header from "./component/Header/Header";
import Sidebar from "./component/Sidebar/Sidebar";
import ZooCages from "./component/ZooCages/ZooCages";

function App() {
    return (
        <div className="main_container">
            <div>
                <Header/>
            </div>
            <div className="main_div">
                <main className="main">
                    <Sidebar/>
                    <ZooCages/>
                </main>
            </div>
        </div>

    );
}

export default App;
