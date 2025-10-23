import { Link } from "react-router-dom";

type NarbarProps = {
  role?: string;
  username?: string;
};

const NavBar = ({ role, username }: NarbarProps) => {

  return (
    <div id="nav">
      <ul>
        <img src="/src/assets/my_fitness_journey.png"></img>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/program">View My Programs</Link></li>
        <li><Link to="/trainers">View Trainers</Link></li>
        {role === "ROLE_TRAINER" &&
          <li>
            <Link to="/trainer-home">Trainer Home</Link>
          </li>
        }
        {role === "ROLE_ADMIN" &&
          <li>
            <Link to="/admin">Admin Dashboard</Link>
          </li>
        }
        {username &&
          <li>
            Current User: {username}
          </li>
        }
        <li className="signin">
          {role ? (
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
