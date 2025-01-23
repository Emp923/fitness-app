import { useEffect } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";

const HomePage = () => {
  const userObj = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");

  useEffect(() => {
    console.log(userObj);
  }, [userObj]);

  return (
    <div className="home">
      <h1>User: {userObj.user.username}</h1>
      <h1>Role: {userObj.user.authorities[0].name}</h1>
      <p>You must be authenticated to see this</p>
    </div>
  );
};

export default HomePage;
