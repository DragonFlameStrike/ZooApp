
import './App.css';
import Header from "./Header/Header";

function App() {
    return (
        <div className="container">
            <Header/>
            <main className="main">
                <section className="content_main">
                    <header className="content__header">
                        <span className="content__header-text">Zoo</span>
                    </header>
                </section>
            </main>
        </div>
    );
}

export default App;
