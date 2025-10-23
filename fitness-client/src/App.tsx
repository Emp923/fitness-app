import { Outlet } from "react-router-dom";
import NavBar from "./components/NavBar";
import { useSelector } from "react-redux";
import { RootState } from "./store";

const App = () => {
  const rawUserInfo = useSelector((state: RootState) => state.auth.userInfo);
  const userObj = rawUserInfo ? JSON.parse(rawUserInfo) : null;
  const role = userObj?.user.authorities[0];
  console.log(userObj);

  return (
    <div id="fitness-app">
      <NavBar role={role?.name} username={userObj?.user.username} />
      <Outlet />
    </div>
  );
}

export default App;
