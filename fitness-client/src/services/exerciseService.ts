type ExerciseQuery = {
  name?: string,
  type?: string,
  muscle?: string,
  difficulty?: string,
};

export const getExercises = async (exerciseQuery: ExerciseQuery) => {
  const API_KEY = import.meta.env.VITE_API_KEY;
  if (!API_KEY) {
    throw Error("API_KEY required to retreive exercises");
  }

  const queryString = `name=${exerciseQuery.name}&type=${exerciseQuery.type}&muscle=${exerciseQuery.muscle}&difficulty=${exerciseQuery.difficulty}`

  return fetch(`/exercises?${queryString}`, {
    headers: { "X-Api-Key": API_KEY }
  });
};
