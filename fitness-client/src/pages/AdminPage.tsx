import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import { getUsers, updateUserRole, User } from "../services/userService";

const AdminPage = () => {
  const { token } = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");
  const [users, setUsers] = useState<User[]>([]);

  const updateRole = async (id: number, role: string) => {
    await updateUserRole(id, role, token);
  };

  useEffect(() => {
    const callApi = async (token: string) => {
      try {
        const users = await getUsers(token);
        setUsers(users);
      } catch (error) {
        console.log(error);
      }
    };

    callApi(token);
  }, [token, updateRole]);

  return (
    <>
      <h1>Admin Dashboard</h1>
      <p>Only Admin should be able to see this screen.</p>
      {users.map((user: User) => (
        <div key={user.id}>
          <h3>{user.username}</h3>
          <p>Role: {user.authorities[0].name}</p>
          <UpdateRoleForm
            userId={user.id}
            userRole={user.authorities[0].name}
            updateRole={updateRole}
          />
        </div>
      ))}
    </>
  );
};

type UpdateRoleFormProps = {
  userId: number,
  userRole: string,
  updateRole: (id: number, role: string) => void
};

const UpdateRoleForm = ({ userId, userRole, updateRole }: UpdateRoleFormProps) => {
  const defaultRole = userRole.split("_")[1].toLowerCase();
  const [role, setRole] = useState<string>(defaultRole);

  return (
    <div className="form-group">
      <label>Update Role: </label>
      <select name="role" defaultValue={role} onChange={(e) => setRole(e.target.value)}>
        <option></option>
        <option value="user">User</option>
        <option value="trainer">Trainer</option>
        <option value="owner">Gym Owner</option>
      </select>
      <button disabled={defaultRole === "admin"} onClick={() => updateRole(userId, role)}>Update</button>
    </div>
  );
};

export default AdminPage;
