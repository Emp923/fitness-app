import { FormEvent, useState } from "react";
import { getExercises } from "../services/exerciseService";
import SearchResults from "./SearchResults";

export type Exercise = {
  name: string,
  type: string,
  muscle: string,
  equipment: string,
  difficulty: string,
  instructions: string
};

const SearchExercises = () => {
  const [name, setName] = useState<string>("");
  const [type, setType] = useState<string>("");
  const [muscle, setMuscle] = useState<string>("");
  const [difficulty, setDifficulty] = useState<string>("");
  const [exercises, setExercises] = useState<Exercise[]>([]);

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();
    const response = await getExercises(
      { name, type, muscle, difficulty }
    );
    if (response.status !== 200) {
      console.log("Error retrieving data");
    }
    const data: Exercise[] = await response.json();
    setExercises(data);
  };

  return (
    <>
      <h1>Search Exercises</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-input-group">
          <label htmlFor="name">Name</label>
          <input
            type="text"
            name="name"
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="form-input-group">
          <label htmlFor="type">Type</label>
          <select name="type" onChange={(e) => setType(e.target.value)}>
            <option></option>
            <option value="cardio">Cardio</option>
            <option value="olympic_weightlifting">Olympic Weightlifting</option>
            <option value="plyometrics">Plyometrics</option>
            <option value="powerlifting">Powerlifting</option>
            <option value="strength">Strength</option>
            <option value="stretching">Stretching</option>
            <option value="strongman">Strongman</option>
          </select>
        </div>
        <div className="form-input-group">
          <label htmlFor="muscle">Muscle</label>
          <select name="muscle" onChange={(e) => setMuscle(e.target.value)}>
            <option></option>
            <option value="abdominals">Abdominals</option>
            <option value="abductors">Abductors</option>
            <option value="adductors">Adductors</option>
            <option value="biceps">Biceps</option>
            <option value="calves">Calves</option>
            <option value="chest">Chest</option>
            <option value="forearms">Forearms</option>
            <option value="glutes">Glutes</option>
            <option value="hamstrings">Hamstrings</option>
            <option value="lats">Lats</option>
            <option value="lower_back">Lower Back</option>
            <option value="middle_back">Middle Back</option>
            <option value="neck">Neck</option>
            <option value="quadriceps">Quadriceps</option>
            <option value="traps">Traps</option>
            <option value="triceps">Triceps</option>
          </select>
        </div>
        <div className="form-input-group">
          <label htmlFor="difficulty">Difficulty</label>
          <select name="difficulty" onChange={(e) => setDifficulty(e.target.value)}>
            <option></option>
            <option value="beginner">Beginner</option>
            <option value="intermediate">Intermediate</option>
            <option value="expert">Expert</option>
          </select>
        </div>
        <button type="submit">Search Exercises</button>
      </form>
      {exercises.length !== 0 ? <SearchResults exercises={exercises} /> : null}
    </>
  );
};

export default SearchExercises;
