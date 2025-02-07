import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";
import { RootState } from "../store";

const AdminRoute = () => {
  const { userInfo } = useSelector((state: RootState) => state.auth);
  if (!userInfo) {
    return <Navigate to="/login" replace />;
  }
  const userObj = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");
  return userObj.user.authorities[0].name === "ROLE_ADMIN" ? <Outlet /> : <Navigate to="/" replace />;
};

export default AdminRoute;
