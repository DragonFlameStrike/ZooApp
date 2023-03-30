import { Routes ,Route } from 'react-router-dom';

import WorkerSidebar from './WorkerSidebar';
import AnimalSidebar from './AnimalSidebar';


interface RouteProps {
    path: string;
    exact?: boolean;
    component: React.ComponentType<any>;
}

const routes: RouteProps[] = [
    {
        path: '/Zoo/workers/',
        component: <WorkerSidebar/>
    },
    {
        path: '/Zoo/animals/',
        component: <AnimalSidebar/>
    },
    {
        path: '/',
    }
];

const Sidebar = (props) => {
    return (
        <Routes>
            {routes.map((route, index) => (
                <Route
                    key={index}
                    path={route.path}
                    element={route.component}
                />
            ))}
        </Routes>

    );
}
export default Sidebar;