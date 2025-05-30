import { Link, Outlet } from "react-router-dom";
import NavBar from "./components/NavBar";

const App = () => {
  return (
    <div id="fitness-app">
      {/* <div id="nav">
        <Link to="/">Home</Link>&nbsp;|&nbsp;
        <Link to="/logout">Logout</Link>&nbsp;|&nbsp;
        <Link to="/program">View My Programs</Link>&nbsp;|&nbsp;
        <Link to="/trainers">View Trainers</Link>&nbsp;|&nbsp;
        <Link to="/trainer-home">Trainer Home</Link>
      </div> */}
      <NavBar role={"ROLE_TRAINER"} />
      <Outlet />
    </div>
  );
}

export default App;
