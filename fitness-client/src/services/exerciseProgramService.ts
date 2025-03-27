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

export const getPrograms = async (token: string): Promise<ProgramExercise[]> => {
  const res = await fetch("/api/programs/program-exercises", {
    method: "GET",
    headers: {"Authorization": `Bearer ${token}`}
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