import { Exercise } from "./SearchExercises";

type Props = {
  exercise: Exercise;
};

const ExerciseDetail = ({ exercise }: Props) => {
  return (
    <>
      <h3>{exercise.name} <i>{exercise.difficulty}</i></h3>
      <ul>
        <li>Muscle: {exercise.muscle}</li>
        <li>Type: {exercise.type}</li>
        <li>Equipment: {exercise.equipment}</li>
      </ul>
      <p>Instructions: {exercise.instructions}</p>
    </>
  );
};

export default ExerciseDetail;
