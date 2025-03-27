import { useState, useEffect, FormEvent } from "react";
import { useSelector } from "react-redux";
import { RootState } from "../store";
import { ProgramBasic, getPrograms, createProgram } from "../services/exerciseProgramService";

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

  const handleSubmit = async (event: FormEvent) => {
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
      <h2>Create New Program</h2>
      <form action="#" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Program Name: </label>
          <input
            type="text"
            name="name"
            onChange={(e) => setProgramName(e.target.value)}
          />
        </div>
        <button type="submit">Save Program</button>
      </form>
      <h2>Available Programs</h2>
      {programs.map(program => (
        <div key={program.id}>
          <h3>{program.name}</h3>
          <p>Created By: {program.createdBy}</p>
        </div>
      ))}
    </>
  );
};

export default TrainerHomePage;
