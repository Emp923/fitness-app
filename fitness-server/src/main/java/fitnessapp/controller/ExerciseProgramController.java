package fitnessapp.controller;

import fitnessapp.dao.ExerciseDao;
import fitnessapp.dao.ProgramExerciseDao;
import fitnessapp.model.Exercise;
import fitnessapp.model.ProgramExercise;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/programs")
public class ExerciseProgramController {

    private final ProgramExerciseDao programDao;
    private final ExerciseDao exerciseDao;

    public ExerciseProgramController(ProgramExerciseDao programExerciseDao, ExerciseDao exerciseDao) {
        this.programDao = programExerciseDao;
        this.exerciseDao = exerciseDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ProgramExercise> listPrograms() {
        return programDao.getProgramExercises();
    }

    @RequestMapping(path = "/exercise", method = RequestMethod.GET)
    public List<Exercise> listExercises() {
        return exerciseDao.getExercise();
    }
}
