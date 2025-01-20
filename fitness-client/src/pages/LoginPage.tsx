import { FormEvent, useState } from "react";
import { Link } from "react-router-dom";
import "./LoginPage.css";

const LoginPage = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    try {
      // process form data
      console.log({ username, password });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div id="login">
      <form onSubmit={handleSubmit}>
        <h1>Please Sign In</h1>
        <div className="form-input-group">
          <label htmlFor="username">Username</label>
          <input
            type="text"
            name="username"
            required
            autoFocus
            onChange={(e) => setUsername(e.target.value)}
          />
        </div>
        <div className="form-input-group">
          <label htmlFor="password">Password</label>
          <input
            type="password"
            name="password"
            required
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">Sign in</button>
        <p>
          Need an account? <Link to="/register">Sign Up!</Link>
        </p>
      </form>
    </div>
  );
};

export default LoginPage;
