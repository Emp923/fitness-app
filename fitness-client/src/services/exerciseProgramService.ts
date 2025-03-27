export type ProgramBasic = {
  id: number,
  name: string,
  createdBy: string
};

export type ProgramDetail = {
  id: number,
  name: string,
  createdBy: string,
  programExercises: ProgramExercise[]
};

export type ProgramExercise = {
  id: number,
  dayOfTheWeek: string,
  exerciseName: string,
  resistance: number,
  sets: number,
  repetitions: number
};

export type ExerciseLog = {
  id: number,
  recordedDate: string,
  resistance: number,
  sets: number,
  repetitions: number,
  exerciseId: number
}

export const getPrograms = async (): Promise<ProgramBasic[]> => {
  const res = await fetch("/api/programs", {
    method: "GET"
  });
  return res.json();
};

export const createProgram = async (program: { name: string }, token: string): Promise<ProgramBasic> => {
  const res = await fetch("/api/programs", {
    method: "POST",
    headers: {
      "Authorization": `Bearer ${token}`,
      "Content-Type": "application/json"
    },
    body: JSON.stringify(program)
  });
  return res.json();
};

export const getProgramDetails = async (programId: number): Promise<ProgramDetail> => {
  const res = await fetch(`/api/programs/${programId}`, {
    method: "GET"
  });
  return res.json();
};

export const getExerciseLog = async (token: string): Promise<ExerciseLog[]> => {
  const res = await fetch("/api/programs/exercise", {
    method: "GET",
    headers: {"Authorization": `Bearer ${token}`}
  });
  return res.json();
};