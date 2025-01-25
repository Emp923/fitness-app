import ExerciseDetail from "./ExerciseDetail";
import { Exercise } from "./SearchExercises";

type Props = {
  exercises: Exercise[]
};

const SearchResults = ({ exercises }: Props) => {
  return (
    <>
      <h1>Search Results</h1>
      {exercises.map((exercise: Exercise) => (
        <ExerciseDetail key={exercise.name} exercise={exercise} />
      ))}
    </>
  );
};

export default SearchResults;
