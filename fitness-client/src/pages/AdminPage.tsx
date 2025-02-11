import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import { getUsers, User } from "../services/userService";

const AdminPage = () => {
  const { token } = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");
  const [users, setUsers] = useState<User[]>([]);

  useEffect(() => {
    const callApi = async (token: String) => {
      try {
        const users = await getUsers(token);
        setUsers(users);
      } catch (error) {
        console.log(error);
      }
    };

    callApi(token);
  }, [token]);

  return (
    <>
      <h1>Admin Dashboard</h1>
      <p>Only Admin should be able to see this screen.</p>
      {users.map((user: User) => (
        <div key={user.id}>
          <h3>{user.username}</h3>
          <p>Role: {user.authorities[0].name}</p>
        </div>
      ))}
    </>
  );
};

export default AdminPage;
