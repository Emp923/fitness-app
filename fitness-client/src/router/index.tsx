import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import App from "../App";
import HomePage from "../pages/HomePage";
import PrivateRoute from "../components/PrivateRoute";
import LoginPage from "../pages/LoginPage";
import LogoutPage from "../pages/LogoutPage";
import RegisterPage from "../pages/RegisterPage";
import SearchExercises from "../components/SearchExercises";
import UserDetails from "../pages/UserDetails";
import AdminPage from "../pages/AdminPage";
import AdminRoute from "../components/AdminRoute";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="" element={<App />}>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/logout" element={<LogoutPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/search" element={<SearchExercises />} />
      <Route path="" element={<PrivateRoute />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/user-details" element={<UserDetails />}/>
        <Route path="/admin" element={<AdminRoute />}>
          <Route path="" element={<AdminPage />} />
        </Route>
      </Route>
    </Route>
  )
);

export default router;
