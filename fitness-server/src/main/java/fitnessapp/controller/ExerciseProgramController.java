package fitnessapp.controller;

import fitnessapp.dao.ExerciseDao;
import fitnessapp.dao.ProgramDao;
import fitnessapp.model.Exercise;
import fitnessapp.model.Program;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/program")
public class ExerciseProgramController {

    private final ProgramDao programDao;
    private final ExerciseDao exerciseDao;

    public ExerciseProgramController(ProgramDao programDao, ExerciseDao exerciseDao){
        this.programDao = programDao;
        this.exerciseDao = exerciseDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Program> listPrograms(){
        return programDao.getProgram();
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Exercise> listExercises(){
        return exerciseDao.getExercise();
    }
}
