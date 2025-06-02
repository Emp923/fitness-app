import { createBrowserRouter, createRoutesFromElements, Route, Navigate } from "react-router-dom";
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
import TrainersPage from "../pages/TrainersPage";
import TrainerRoute from "../components/TrainerRoute";
import TrainerHomePage from "../pages/TrainerHomePage";
import ProgramPage from "../pages/ProgramPage";
import ExerciseProgramPage from "../pages/ExerciseProgramPage";
import MyExerciseInstructionPage from "../pages/MyExerciseInstructionPage";
import TrainersViewUsersPage from "../pages/TrainersViewUsersPage";
import LandingPage from "../pages/LandingPage";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="" element={<App />}>
      <Route index element={<LandingPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/logout" element={<LogoutPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/search" element={<SearchExercises />} />
      <Route path="" element={<PrivateRoute />}>
        <Route path="/home" element={<HomePage />} />
        <Route path="/user-details" element={<UserDetails />}/>
        <Route path="/program/:id" element={<ProgramPage />} />
        <Route path="/admin" element={<AdminRoute />}>
          <Route path="" element={<AdminPage />} />
        </Route>
        <Route path="/trainer-home" element={<TrainerRoute />}>
          <Route path="" element={<TrainerHomePage />} />
          <Route path="/trainer-home/user-details-view" element={<TrainersViewUsersPage />} />
        </Route>
        <Route path="/trainers" element={<TrainersPage/>}/>
        <Route path="/program" element={<ExerciseProgramPage/>}/>
        <Route path="/exercise/:exerciseName" element={<MyExerciseInstructionPage/>}/>
      </Route>
    </Route>
  )
);

export default router;
