import { createBrowserRouter, createRoutesFromElements, Route } from "react-router-dom";
import App from "../App";
import HomePage from "../pages/HomePage";
import PrivateRoute from "../components/PrivateRoute";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="" element={<App />}>
      <Route path="" element={<PrivateRoute />}>
        <Route path="/" element={<HomePage />} />
      </Route>
    </Route>
  )
);

export default router;
