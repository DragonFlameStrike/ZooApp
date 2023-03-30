const WorkerSidebar = (props) => {
    return(
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
    )
}
export default WorkerSidebar;