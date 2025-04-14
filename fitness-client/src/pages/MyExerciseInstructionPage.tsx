import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getExerciseByName } from "../services/exerciseService";
import { Exercise } from "../components/SearchExercises";
import MarkProgramCard from "../components/MarkProgramCard";

const MyExerciseInstructionPage = () => {
    const { exerciseName } = useParams();
    const [exercise, setExercise] = useState<Exercise | null>(null);
    
    useEffect(() => {
        const fetchExercise = async () => {
            try{
                const res = await getExerciseByName(decodeURIComponent(exerciseName || ""));
                const data = await res.json();
                setExercise(data[0]);
            }catch(error){
                console.error(error);
            }
        };

        fetchExercise();

    },[exerciseName]);

    if(!exercise){
        return <h1>Loading...</h1>
    }

    return (
        <div>
            <h1>Exercise Instructions</h1>
            <div>
                <h2>{exercise.name}</h2>
                <p><strong>Equipment:</strong>{exercise.equipment}</p>
                <p><strong>Instructions:</strong>{exercise.instructions}</p>
            </div>
            <h1>Mark My Progress</h1>
            <div className="mark-progress-container">
                <MarkProgramCard exerciseName={decodeURIComponent(exerciseName || "")}/>
            </div>
        </div>
    );
};

export default MyExerciseInstructionPage;