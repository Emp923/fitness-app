import { useParams } from "react-router-dom";
import { useEffect, useState, FormEvent } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import {
  ProgramDetail,
  ProgramExercise,
  assignProgramExerciseToProgram,
  getProgramDetails,
  getProgramExercises
} from "../services/exerciseProgramService";

const ProgramPage = () => {
  const { token } = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");
  const { id } = useParams();
  const [program, setProgram] = useState<ProgramDetail | null>(null);
  const [programExercises, setProgramExercises] = useState<ProgramExercise[]>([]);
  const [selectedExerciseId, setSelectedExerciseId] = useState<number>(0);

  const handleFormSubmit = (event: FormEvent) => {
    event.preventDefault();
    if (selectedExerciseId === 0) {
      return;
    }

    assignProgramExerciseToProgram(id as unknown as number, selectedExerciseId, token);
    setSelectedExerciseId(0);
  };

  useEffect(() => {
    const callApi = async () => {
      try {
        const program = await getProgramDetails(Number(id));
        setProgram(program);
      } catch (error) {
        console.log(error);
      }
    };

    callApi();
  }, []);

  useEffect(() => {
    const callApi = async () => {
      try {
        let programExercises = await getProgramExercises();
        program?.programExercises.forEach(programPE => {
          programExercises = programExercises.filter(pe => pe.id !== programPE.id);
        });
        setProgramExercises(programExercises);
      } catch (error) {
        console.log(error);
      }
    };

    callApi();
  }, [program]);

  if (!program) {
    return <h1>Loading...</h1>;
  }

  return (
    <div>
      <h1>{program.name}</h1>
      <p>Created By: {program.createdBy}</p>
      <div style={{ display: "flex" }}>
        <div style={{ width: 400 }}>
          <h2>Exercises</h2>
          {program.programExercises.map(exercise => (
            <div key={exercise.id}>
              <h3>{exercise.exerciseName}</h3>
              <ul>
                <li>Days: {exercise.dayOfTheWeek}</li>
                <li>Resistance: {exercise.resistance}</li>
                <li>Sets: {exercise.sets}</li>
                <li>Reps: {exercise.repetitions}</li>
              </ul>
            </div>
          ))}
        </div>
        <div>
          <h2>Assign Exercise to this Program</h2>
          <form action="#" onSubmit={handleFormSubmit}>
            <div className="form-group">
              <label>Select Exercise:</label>
              <select value={selectedExerciseId} onChange={(e) => setSelectedExerciseId(e.target.value as unknown as number)}>
                <option value={0}>Select Exercise...</option>
                {programExercises.map(programExercise => (
                  <option key={programExercise.id} value={programExercise.id}>
                    {programExercise.exerciseName}
                  </option>
                ))}
              </select>
            </div>
            <button type="submit">Assign this Exercise</button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ProgramPage;
