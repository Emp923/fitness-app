package fitnessapp.dao;

import fitnessapp.model.Exercise;

import java.util.List;

public interface ExerciseDao {

    List<Exercise> getExercise();

    Exercise getExerciseById(int id);

    List<Exercise> getExerciseLogByUserId(int userId);

    Exercise submitExercise(Exercise exercise);

}
