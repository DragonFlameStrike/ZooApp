
import './App.css';
import Header from "./Header/Header";

function App() {
    return (
        <div className="main_container">
            <div>
                <Header/>
            </div>
            <div className="main_div">
                <main className="main">
                    <aside className="sidebar">
                        <header className="sidebar__header">Workers:</header>

                        <section className="sidebar__content">
                            <div className="sidebar__menu">
                                <a href="#" className="sidebar__menu-item">Worker 1</a>
                                <a href="#" className="sidebar__menu-item">Worker 2</a>
                                <a href="#" className="sidebar__menu-item">Worker 3</a>
                            </div>
                        </section>

                        <footer className="sidebar__footer">
                            <button className="sidebar__submit-button">Hire new worker</button>
                        </footer>
                    </aside>

                    <section className="content">
                        <div className="section">
                            <p>Cage 1</p>
                        </div>
                        <div className="section">
                            <p>Cage 2</p>
                        </div>
                        <div className="section">
                            <p>Cage 3</p>
                        </div>
                        <div className="section">
                            <p>Cage 4</p>
                        </div>
                        <div className="section">
                            <p>Cage 5</p>
                        </div>
                        <div className="section">
                            <p>Cage 6</p>
                        </div>
                    </section>
                </main>
            </div>
        </div>

    );
}

export default App;
