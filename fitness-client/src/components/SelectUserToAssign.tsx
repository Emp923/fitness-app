import { useState, useEffect, FormEvent } from "react";
import { UserDetails, getUserDetails } from "../services/userDetailsService";
import { assignProgramToUser } from "../services/exerciseProgramService";

type SelectUserToAssignProps = {
  programId: number,
  token: string
};

const SelectUserToAssign = ({ programId, token }: SelectUserToAssignProps) => {
  const [userDetails, setUserDetails] = useState<UserDetails[]>([]);
  const [selectedUserId, setSelectedUserId] = useState<number>(0);

  useEffect(() => {
    const callApi = async () => {
      const userDetails = await getUserDetails(token);
      setUserDetails(userDetails);
    };

    callApi();
  }, []);

  const clearForm = () => {
    setSelectedUserId(0);
  };

  const handleAssignment = (event: FormEvent) => {
    event.preventDefault();
    if (selectedUserId === 0) {
      return;
    }

    try {
      assignProgramToUser(programId, selectedUserId, token);
      alert(`Program was assigned successfully`);
    } catch (error) {
      console.log(error);
    }

    clearForm();
  };

  return (
    <div>
      <h2>Select User</h2>
      <form action="#" onSubmit={handleAssignment}>
        <label>Select User</label>
        <select value={selectedUserId} onChange={(e) => setSelectedUserId(Number(e.target.value))}>
          <option value={0}>---</option>
          {userDetails.map((userDetail) => (
            <option key={userDetail.userId} value={userDetail.userId}>
              {userDetail.preferredName}
            </option>
          ))}
        </select>
        <button type="submit">Assign this user to program</button>
      </form>
    </div>
  );
};

export default SelectUserToAssign;
