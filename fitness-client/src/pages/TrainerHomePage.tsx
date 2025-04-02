import { useState, useEffect, FormEvent } from "react";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import {
  ProgramBasic,
  getPrograms,
  createProgram,
  ProgramExercise,
  createProgramExercise
} from "../services/exerciseProgramService";

const daysOfWeek = [
  "Monday",
  "Tuesday",
  "Wednesday",
  "Thursday",
  "Friday",
  "Saturday",
  "Sunday",
];

const TrainerHomePage = () => {
  const { token } = JSON.parse((useSelector((state:RootState) => state.auth)).userInfo || "");
  const [programs, setPrograms] = useState<ProgramBasic[]>([]);
  const [programName, setProgramName] = useState<string>("");

  useEffect(() => {
    const callApi = async () => {
      try {
        const programs = await getPrograms();
        setPrograms(programs);
      } catch (error) {
        console.log(error);
      }
    };

    callApi();
  }, []);

  const handleCreateProgramSubmit = async (event: FormEvent) => {
    event.preventDefault();
    
    const formData = {
      name: programName
    };

    try {
      const res = await createProgram(formData, token);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <h1>Trainer Home Page</h1>
      <div style={{ display: "flex" }}>
        <CreateProgramForm
          handleSubmit={handleCreateProgramSubmit}
          setProgramName={setProgramName}
        />
        <CreateProgramExerciseForm
          token={token}
        />
      </div>
      <h2>Available Programs</h2>
      {programs.map(program => (
        <div key={program.id}>
          <Link to={`/program/${program.id}`}>
            <h3>{program.name}</h3>
          </Link>
          <p>Created By: {program.createdBy}</p>
        </div>
      ))}
    </>
  );
};

type CreateProgramFormProps = {
  handleSubmit: (event: FormEvent) => void,
  setProgramName: (programName: string) => void
};

const CreateProgramForm = ({ handleSubmit, setProgramName }: CreateProgramFormProps) => {
  return (
    <div style={{ width: 500 }}>
      <h2>Create New Program</h2>
      <form action="#" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Program Name:</label>
          <input
            type="text"
            name="name"
            onChange={(e) => setProgramName(e.target.value)}
          />
        </div>
        <button type="submit">Save Program</button>
      </form>
    </div>
  );
};

type CreateProgramExerciseFormProps = {
  token: string
};

const CreateProgramExerciseForm = ({ token }: CreateProgramExerciseFormProps) => {
  const [dayOfTheWeek, setDayOfTheWeek] = useState<string[]>([]);
  const [exerciseName, setExerciseName] = useState<string>("");
  const [resistance, setResistance] = useState<number>(0);
  const [sets, setSets] = useState<number>(0);
  const [repetitions, setRepetitions] = useState<number>(0);

  const handleDayCheckboxChange = (day: string) => {
    setDayOfTheWeek(prev =>
      prev.includes(day) ? prev.filter(d => d !== day) : [...prev, day]
    );
  };

  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();

    const programExercise: ProgramExercise = {
      dayOfTheWeek: dayOfTheWeek.toString(),
      exerciseName,
      resistance,
      sets,
      repetitions
    };

    try {
      const res = createProgramExercise(programExercise, token);
      setDayOfTheWeek([]);
      setExerciseName("");
      setResistance(0);
      setSets(0);
      setRepetitions(0);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div style={{ width: 500 }}>
      <h2>Create New Exercise Program</h2>
      <form action="#" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Exercise Name:</label>
          <input
            type="text"
            name="exerciseName"
            value={exerciseName}
            onChange={(e) => setExerciseName(e.target.value)}
          />
        </div>
        <div className="form-group">
          <label>Days: (Select all that apply)</label>
          <div className="checkbox-group">
            {daysOfWeek.map(day => (
              <div key={day} className="checkbox-item">
                <label>
                  <input
                    type="checkbox"
                    value={day}
                    checked={dayOfTheWeek.includes(day)}
                    onChange={() => handleDayCheckboxChange(day)}
                  />
                  {day}
                </label>
              </div>
            ))}
          </div>
        </div>
        <div className="form-group">
          <label>Resistance:</label>
          <input
            type="number"
            name="resistance"
            value={resistance}
            onChange={(e) => setResistance(e.target.value as unknown as number)}
          />
        </div>
        <div className="form-group">
          <label>Sets:</label>
          <input
            type="number"
            name="sets"
            value={sets}
            onChange={(e) => setSets(e.target.value as unknown as number)}
          />
        </div>
        <div className="form-group">
          <label>Repetitions:</label>
          <input
            type="number"
            name="repetitions"
            value={repetitions}
            onChange={(e) => setRepetitions(e.target.value as unknown as number)}
          />
        </div>
        <button type="submit">Save Exercise</button>
      </form>
    </div>
  );
};

export default TrainerHomePage;
