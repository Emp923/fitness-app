import { Navigate, Outlet } from "react-router-dom";

const PrivateRoute = () => {
  // TODO: check for auth state
  const { userInfo } = { userInfo: true };
  return userInfo ? <Outlet /> : <Navigate to="/login" replace />;
};

export default PrivateRoute;
