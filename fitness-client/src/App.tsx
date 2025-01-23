import { Link, Outlet } from "react-router-dom";

const App = () => {
  return (
    <div id="fitness-app">
      <div id="nav">
        <Link to="/">Home</Link>
        <Link to="/logout">Logout</Link>
      </div>
      <Outlet />
    </div>
  );
}

export default App;
