import { useEffect } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import AdminPage from "./AdminPage";
import TrainerHomePage from "./TrainerHomePage";
import { useNavigate } from "react-router-dom";
import { getMyUserDetails } from "../services/userDetailsService";

const HomePage = () => {
  const userObj = JSON.parse((useSelector((state: RootState) => state.auth)).userInfo || "");
  const role = userObj.user.authorities[0].name;
  const token = userObj.token;
  const userId = userObj.user.id;

  const navigate = useNavigate();

  useEffect(() => {
    const checkUserDetails = async () => {
      if (role === "ROLE_USER") {
        try {
          const details = await getMyUserDetails(token);
          if (!details || !details.preferredName) {
            navigate("/user-details");
          }
        } catch {
          navigate("/user-details");
        }
      }
    };
    checkUserDetails();
  }, [role, token, userId, navigate]);

  return (
    <div className="home">
      <h1>User: {userObj.user.username}</h1>
      {/*<h1>Role: {role}</h1>
      <p>You must be authenticated to see this</p>*/}

      {role === "ROLE_ADMIN" && <AdminPage />}
      {role === "ROLE_TRAINER" && <TrainerHomePage />}
    </div>
  );
};

export default HomePage;
