import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const LogoutPage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    // TODO: logout logic goes here

    console.log('you have been logged out');
    navigate("/login");
  }, []);

  return (
    <h1>Logout</h1>
  );
};

export default LogoutPage;
