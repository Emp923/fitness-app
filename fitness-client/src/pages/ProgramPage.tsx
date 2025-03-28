import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { ProgramDetail, getProgramDetails } from "../services/exerciseProgramService";

const ProgramPage = () => {
  const { id } = useParams();
  const [program, setProgram] = useState<ProgramDetail | null>(null);

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

  if (!program) {
    return <h1>Loading...</h1>;
  }

  return (
    <>
      <h1>{program.name}</h1>
      <p>Created By: {program.createdBy}</p>
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
    </>
  );
};

export default ProgramPage;
