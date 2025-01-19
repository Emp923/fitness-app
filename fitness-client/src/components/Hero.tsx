import { Link } from "react-router-dom";

const Hero = () => {
  return (
    <>
      <h1>My Fitness Journey</h1>
      <p>
        This is a boilerplate authentication that stores a JWT in
        an HTTP-Only cookie.
      </p>
      <Link to="/login">Sign In</Link>
      <Link to="/register">Register</Link>
    </>
  );
};

export default Hero;
