export type ProgramExercise = {
    id: number,
    dayOfTheWeek: string,
    exerciseName: string,
    resistance: number,
    sets: number,
    repetitions: number
};

export type ExerciseLog = {
    id?: number,
    recordedDate: string | null,
    resistance: number,
    sets: number,
    repetitions: number,
    exerciseName: string,
    comments?: string
}

export const getProgramExercises = async (token: string): Promise<ProgramExercise[]> => {
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

export const submitExerciseLog = async (exerciseSubmit: ExerciseLog, token: string): Promise<ExerciseLog> => {
    const res = await fetch("/api/programs/submit-exercise-log", {
        method: "POST",
        body: JSON.stringify(exerciseSubmit),
        headers: {"Authorization": `Bearer ${token}`, "Content-Type": "application/json"}
    });
    return res.json();
};