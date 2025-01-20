import { FormEvent, useState } from "react";
import { Link } from "react-router-dom";
import "./LoginPage.css";

const RegisterPage = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");

  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();
    try {
      if (password !== confirmPassword) {
        throw Error("Password error");
      }
      console.log({ username, password });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div id="register" className="text-center">
      <h1>Create Account</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-input-group">
          <label htmlFor="username">Username</label>
          <input
            type="text"
            name="username"
            placeholder="username"
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
            placeholder="password"
            required
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div className="form-input-group">
          <label htmlFor="confirmPassword">Confirm Password</label>
          <input
            type="password"
            name="confirmPassword"
            placeholder="confirm password"
            required
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
        </div>
        <button type="submit">Create Account</button>
        <p>Already have an account? <Link to="/login">Log In</Link></p>
      </form>
    </div>
  );
};

export default RegisterPage;
