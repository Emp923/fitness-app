import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import { getUserDetails } from "../services/userDetailsService";
import type { UserDetails } from "../services/userDetailsService";
import ViewUserExerciseProgress from "../components/ViewUserExerciseProgress";

const TrainersViewUsersPage = () => {
    const { token } = JSON.parse(useSelector((state: RootState) => state.auth).userInfo || "");
    const [users, setUsers] = useState<UserDetails[]>([]);
    const [selectedUserId, setSelectedUserId] = useState<number | null>(null);
    const [selectedUser, setSelectedUser] = useState<UserDetails | null>(null);


    useEffect(() => {
        const fetchUsers = async () => {
            if (!token) {
                console.log("No token found");
                return;
            }
            const allUserDetails = await getUserDetails(token);
            setUsers(allUserDetails);
        };
        fetchUsers();
    }, [token]);

    useEffect(() => {
        const user = users.find((u) => u.userId === selectedUserId) || null;
        setSelectedUser(user);
    }, [selectedUserId, users]);

    return (
        <div>
            <h1>View User Details</h1>

            <label>Select a user:</label>
            <select onChange={(e) => setSelectedUserId(Number(e.target.value))} defaultValue="">
                <option value="" disabled>
                    -- Choose a user --
                </option>
                {users.map((user) => (
                    <option key={user.userId} value={user.userId}>
                        {user.preferredName}
                    </option>
                ))}
            </select>

            {selectedUser && (
                <div>
                    <p><strong>Preferred Name:</strong> {selectedUser.preferredName}</p>
                    <p><strong>Birthday:</strong> {selectedUser.birthday}</p>
                    <p><strong>Gender:</strong> {selectedUser.gender}</p>
                    <p><strong>Availability:</strong> {selectedUser.availability}</p>
                    <p><strong>Restrictions:</strong> {selectedUser.restrictions}</p>
                    <p><strong>Goals:</strong> {selectedUser.goals}</p>
                    <p><strong>Comments:</strong> {selectedUser.comments}</p>
                </div>
            )}
            <ViewUserExerciseProgress userDetails={users}/>
        </div>
    );

};

export default TrainersViewUsersPage;