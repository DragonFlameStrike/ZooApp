const AnimalSidebar = (props) => {
    return(
        <aside className="sidebar">
            <header className="sidebar__header">Animals:</header>

            <section className="sidebar__content">
                <div className="sidebar__menu">
                    <a href="#" className="sidebar__menu-item">Animal 1</a>
                    <a href="#" className="sidebar__menu-item">Animal 2</a>
                    <a href="#" className="sidebar__menu-item">Animal 3</a>
                </div>
            </section>

            <footer className="sidebar__footer">
                <button className="sidebar__submit-button" onClick={props.toggle}>Get new Animal</button>
            </footer>
        </aside>
    )
}
export default AnimalSidebar;