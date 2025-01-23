import { FormEvent, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./LoginPage.css";
import { login } from "../services/authService";
import { useDispatch, useSelector } from "react-redux";
import { setCredentials } from "../slices/authSlice";
import { RootState } from "../store";

const LoginPage = () => {
  const [username, setUsername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [loginError, setLoginError] = useState<boolean>(false);

  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { userInfo } = useSelector((state: RootState) => state.auth);

  useEffect(() => {
    if (userInfo) {
      navigate("/");
    }
  }, [navigate, userInfo]);

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    try {
      // process form data
      const credentials = { username, password };
      const response = await login(credentials);
      if (response.status === 200) {
        const data = await response.json();
        dispatch(setCredentials({ ...data }));
        setLoginError(false);
        navigate("/");
      } else {
        throw Error("Invalid credentials");
      }
    } catch (error) {
      console.log(error);
      setLoginError(true);
    }
  };

  return (
    <div id="login">
      <form onSubmit={handleSubmit}>
        <h1>Please Sign In</h1>
        {loginError ? <p style={{ color: 'red' }}>Invalid Credentials</p> : null}
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
