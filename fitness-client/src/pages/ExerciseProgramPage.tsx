import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import {
    getProgramExercises, getExerciseLog, ExerciseLog,
    ProgramExercise
} from "../services/exerciseProgramService";
import ViewProgramCard from "../components/ViewProgramCard";

const daysOfWeek = [
    "All",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday"
];

const ExerciseProgramPage = () => {
    const { token } = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");
    const [programs, setPrograms] = useState<ProgramExercise[]>([]);
    const [exercises, setExercises] = useState<ExerciseLog[]>([]);
    const [selectedDay, setSelectedDay] = useState<string>("All");

    useEffect(() => {
        const callApi = async (token: string) => {
            try{
                const programs = await getProgramExercises(token);
                console.log(programs);
                setPrograms(programs);
                const exercises = await getExerciseLog(token);
                setExercises(exercises);
            }catch(error){
                console.log(error);
            }
        };

        callApi(token);
    },[token]);

    const filteredPrograms = selectedDay === "All" 
        ? programs : programs.filter((exercise) =>
            exercise.dayOfTheWeek.toLowerCase().includes(selectedDay.toLowerCase())
    );

    return (
        <>
            <h1>My Programs</h1>
            <div>
                <label htmlFor="dayFilter"><strong>Filter by Day:</strong></label>
                <select
                    id="dayFilter"
                    value={selectedDay}
                    onChange={(e) => setSelectedDay(e.target.value)}
                >
                    {daysOfWeek.map(day => (
                        <option key={day} value={day}>{day}</option>
                    ))}
                </select>
            </div>
            <div className="program-container">
                {filteredPrograms.map((programExercise: ProgramExercise) => (
                    <ViewProgramCard key={programExercise.id} programExercise={programExercise}/>
                ))}
            </div>
        </>
    );
};

export default ExerciseProgramPage;