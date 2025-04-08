package fitnessapp.dao;

import fitnessapp.model.ProgramExercise;
import fitnessapp.model.ProgramExerciseDto;

import java.util.List;

public interface ProgramExerciseDao {

    List<ProgramExercise> getProgramExercises();

    ProgramExercise getProgramExerciseById(int programExerciseId);

    List<ProgramExercise> getProgramExercisesByProgramId(int programId);

    ProgramExercise createProgramExercise(ProgramExerciseDto programExerciseDto);

    void assignProgramExerciseToProgram(int programExerciseId, int programId);

    boolean isProgramExerciseAssignedToProgram(int programExerciseId, int programId);
}
