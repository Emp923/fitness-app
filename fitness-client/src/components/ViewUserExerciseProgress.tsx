import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import { ExerciseLog, getExerciseLogByUserId, getProgramExercisesById } from "../services/exerciseProgramService";
import { formatDate } from "../utils.ts";
import { UserDetails } from "../services/userDetailsService.ts";

type ViewUserExerciseProgressProps = {
    userDetails: UserDetails[]
};

const ViewUserExerciseProgress = ({ userDetails }: ViewUserExerciseProgressProps) => {
    const { token } = JSON.parse(useSelector((state: RootState) => state.auth).userInfo || "");
    const [selectedUserId, setSelectedUserId] = useState<number | null>(null);
    const [exerciseLogs, setExerciseLogs] = useState<ExerciseLog[]>([]);
    const [selectedDate, setSelectedDate] = useState<Date | null>(new Date());

    const setExerciseNameWithId = async (exerciseLog: ExerciseLog): Promise<ExerciseLog> => {
        const programExercise = await getProgramExercisesById(exerciseLog.exerciseId);
        return {...exerciseLog, exerciseName: programExercise.exerciseName};
    }

    useEffect(() => {
        const fetchExerciseLogs = async () => {
            if (!token || selectedUserId === null) {
                return;
            }
            const logs = await getExerciseLogByUserId(token, selectedUserId);
            const logsWithNames = await Promise.all(logs.map(setExerciseNameWithId));
            setExerciseLogs(logsWithNames);
        };
        fetchExerciseLogs();
    }, [token, selectedUserId]);

    const formattedDate = formatDate(selectedDate?.toISOString());

    const filteredExercises = exerciseLogs.filter(
        (log) => log.recordedDate === formattedDate
    );

    return (
        <div>
            <h1>View User Exercise Progress</h1>

            <label>Select a user:</label>
            <select onChange={(e) => setSelectedUserId(Number(e.target.value))} defaultValue="">
                <option value="" disabled>
                    -- Choose a user --
                </option>
                {userDetails.map((userDetail) => (
                    <option key={userDetail.userId} value={userDetail.userId}>
                        {userDetail.preferredName}
                    </option>
                ))}
            </select>

            <div>
                <label>Select a date:</label>
                <DatePicker selected={selectedDate} onChange={(date) => setSelectedDate(date)} />
            </div>

            {filteredExercises.length > 0 ? (
                <div>
                    <h3>Exercises on {formattedDate}</h3>
                    <ul>
                        {filteredExercises.map((exercise) => (
                            <li key={exercise.id}>
                                <p><strong>{exercise.exerciseName}:</strong></p>
                                <p><strong>Sets: </strong>{exercise.sets}</p>
                                <p><strong>Repetitions: </strong>{exercise.repetitions}</p>
                                {exercise.comments && (
                                    <div><em>User Comment:</em> {exercise.comments}</div>
                                )}
                            </li>
                        ))}
                    </ul>
                </div>
            ) : (
                selectedUserId && <p>No exercises logged for this date.</p>
            )}
        </div>
    );
};

export default ViewUserExerciseProgress;