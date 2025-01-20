import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import App from "../App";
import HomePage from "../pages/HomePage";
import PrivateRoute from "../components/PrivateRoute";
import LoginPage from "../pages/LoginPage";
import LogoutPage from "../pages/LogoutPage";
import RegisterPage from "../pages/RegisterPage";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="" element={<App />}>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/logout" element={<LogoutPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="" element={<PrivateRoute />}>
        <Route path="/" element={<HomePage />} />
      </Route>
    </Route>
  )
);

export default router;
