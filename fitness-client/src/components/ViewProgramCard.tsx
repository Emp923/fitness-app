import {ProgramExercise} from "../services/exerciseProgramService";
import { useNavigate } from "react-router-dom";
import "../pages/ExerciseProgramPage";

type Props = {
    programExercise: ProgramExercise;
};

const ViewProgramCard = ({programExercise}: Props) => {
    const navigate = useNavigate();

    const handleViewInstructions = () => {
        navigate(`/exercise/${encodeURIComponent(programExercise.exerciseName)}`);
    };

    return (
        <div className="view-program-card">
            <h2>{programExercise.dayOfTheWeek}</h2>
            <h2>{programExercise.exerciseName}</h2>
            <p><strong>Weight: </strong>{programExercise.resistance}</p>
            <p><strong>Sets: </strong>{programExercise.sets}</p>
            <p><strong>Repetitions: </strong>{programExercise.repetitions}</p>
            <button onClick={handleViewInstructions}>View Instructions</button>
        </div>
    );

};

export default ViewProgramCard;