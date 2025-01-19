import { FormEvent, useState } from "react";
import { Link } from "react-router-dom";

const RegisterPage = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [role, setRole] = useState<string>("ROLE_USER");

  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();
    try {
      if (password !== confirmPassword) {
        throw Error("Password error");
      }
      console.log({ username, password, role });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <h1>Registration Form</h1>
      <form onSubmit={handleSubmit}>
        <label>Username: </label>
        <input
          type="text"
          name="username"
          placeholder="username"
          onChange={(e) => setUsername(e.target.value)}
        />
        <label>Password: </label>
        <input
          type="password"
          name="password"
          placeholder="password"
          onChange={(e) => setPassword(e.target.value)}
        />
        <label>Confirm Password: </label>
        <input
          type="password"
          name="confirmPassword"
          placeholder="confirm password"
          onChange={(e) => setConfirmPassword(e.target.value)}
        />
        <label>Select Role: </label>
        <select name="role" onChange={(e) => setRole(e.target.value)}>
          <option value="ROLE_USER">User</option>
          <option value="ROLE_ADMIN">Admin</option>
        </select>
        <button type="submit">Sign Up!</button>
      </form>

      <p>Already signed up? <Link to="/login">Log In</Link></p>
    </>
  );
};

export default RegisterPage;
