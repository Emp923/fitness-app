import { Link, Outlet } from "react-router-dom";
import { useSelector } from "react-redux";
import { RootState } from "../store";

type NarbarProps = {
    role?: string;
};

const NavBar = ({ role }: NarbarProps) => {
    //const userObj = JSON.parse((useSelector((state: RootState) => state.auth)).userInfo || "");
    const rawUserInfo = useSelector((state: RootState) => state.auth.userInfo);
    const userObj = rawUserInfo ? JSON.parse(rawUserInfo) : null;

    return (
        <div id="nav">
            <ul>
                <img src="/src/assets/my_fitness_journey.png"></img>
                <li><Link to="/">Home</Link></li>
                <li><Link to="/program">View My Programs</Link></li>
                <li><Link to="/trainers">View Trainers</Link></li>
                <li>{role === "ROLE_TRAINER" && <Link to="/trainer-home">Trainer Home</Link>}</li>
                <li className="signin">
                    {userObj ? (
                        <Link to="/logout">Logout</Link>
                    ) : (
                        <Link to="/login">Login</Link>
                    )}
                </li>
            </ul>
        </div>
    )

};

export default NavBar;