package fitnessapp.controller;

import fitnessapp.dao.ExerciseDao;
import fitnessapp.dao.ProgramDao;
import fitnessapp.dao.ProgramExerciseDao;
import fitnessapp.dao.UserDao;
import fitnessapp.exception.DaoException;
import fitnessapp.model.*;
import fitnessapp.security.SecurityUtils;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/programs")
public class ExerciseProgramController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseProgramController.class);

    private final ProgramDao programDao;
    private final ProgramExerciseDao programExerciseDao;
    private final ExerciseDao exerciseDao;
    private final UserDao userDao;

    public ExerciseProgramController(ProgramDao programDao,
                                     ProgramExerciseDao programExerciseDao,
                                     ExerciseDao exerciseDao,
                                     UserDao userDao) {
        this.programDao = programDao;
        this.programExerciseDao = programExerciseDao;
        this.exerciseDao = exerciseDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ProgramBasicDto> listPrograms() {
        return programDao.listAllPrograms();
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Program createNewProgram(@RequestBody ProgramCreateDto programCreateDto) {
        if (programCreateDto == null || programCreateDto.getName() == null || programCreateDto.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required field 'name' is missing or blank");
        }

        User currentTrainer = userDao.getUserByUsername(SecurityUtils.getCurrentUsername());

        Program newProgram = null;

        try {
            newProgram = programDao.createProgram(currentTrainer.getId(), programCreateDto);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return newProgram;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ProgramDetailDto getProgramById(@PathVariable("id") int programId) {
        ProgramDetailDto program = programDao.getProgramDetailById(programId);

        if (program == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("program with id: %s was not found", programId));
        }

        List<ProgramExercise> programExercises = programExerciseDao.getProgramExercisesByProgramId(programId);
        List<ProgramExerciseDto> programExerciseDtos = new ArrayList<>();
        for (ProgramExercise programExercise : programExercises) {
            programExerciseDtos.add(mapProgramExerciseToProgramExerciseDto(programExercise));
        }
        program.setProgramExercises(programExerciseDtos);

        return program;
    }

    @RequestMapping(path = "/my-programs", method = RequestMethod.GET)
    public List<ProgramBasicDto> viewUserPrograms() {
        User currentUser = userDao.getUserByUsername(SecurityUtils.getCurrentUsername());

        if (currentUser == null) {
            LOGGER.debug("Unknown username");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown username");
        }

        return programDao.getProgramsByUserId(currentUser.getId());
    }

    @RequestMapping(path = "/my-exercises", method = RequestMethod.GET)
    public List<ProgramExerciseDto> viewUserExercises() {
        User currentUser = userDao.getUserByUsername(SecurityUtils.getCurrentUsername());

        if (currentUser == null) {
            LOGGER.debug("Unknown username");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unknown username");
        }
        return this.programExerciseDao.getProgramExercisesByUserId(currentUser.getId());
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{programId}/assign-to-user/{userId}", method = RequestMethod.POST)
    public void assignProgramToUser(@PathVariable("programId") int programId,
                                    @PathVariable("userId") int userId) {

        this.getProgramById(programId);
        if (userDao.getUserById(userId) == null) {
            String message = String.format("User with id: %s was not found", userId);
            LOGGER.debug(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        }

        if (this.programDao.isProgramAssignedToUser(programId, userId)) {
            LOGGER.debug("Program to User relationship already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "relationship already exists");
        }

        programDao.assignProgramToUser(programId, userId);
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{programId}/assign-program-exercise/{programExerciseId}", method = RequestMethod.POST)
    public void assignProgramExerciseToProgram(@PathVariable("programId") int programId,
                                               @PathVariable("programExerciseId") int programExerciseId) {

        this.getProgramById(programId);
        this.getProgramExerciseById(programExerciseId);

        if (this.programExerciseDao.isProgramExerciseAssignedToProgram(programExerciseId, programId)) {
            LOGGER.debug("Program to Program Exercise relationship already exists");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "relationship already exists");
        }

        programExerciseDao.assignProgramExerciseToProgram(programExerciseId, programId);
    }

    @RequestMapping(path = "/program-exercises", method = RequestMethod.GET)
    public List<ProgramExercise> listProgramExercises() {
        return programExerciseDao.getProgramExercises();
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/program-exercises", method = RequestMethod.POST)
    public ProgramExerciseDto createProgramExercise(@Valid @RequestBody ProgramExerciseDto programExerciseDto) {
        User currentTrainer = userDao.getUserByUsername(SecurityUtils.getCurrentUsername());

        // TODO check for null input fields

        ProgramExerciseDto newProgramExerciseDto = null;

        try {
            ProgramExercise newProgramExercise = programExerciseDao.createProgramExercise(programExerciseDto);
            newProgramExerciseDto = mapProgramExerciseToProgramExerciseDto(newProgramExercise);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return newProgramExerciseDto;
    }

    @RequestMapping(path = "/program-exercises/{id}", method = RequestMethod.GET)
    public ProgramExerciseDto getProgramExerciseById(@PathVariable("id") int programExerciseId) {
        ProgramExercise programExercise = programExerciseDao.getProgramExerciseById(programExerciseId);

        if (programExercise == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("program exercise with id: %s was not found", programExerciseId));
        }

        return mapProgramExerciseToProgramExerciseDto(programExercise);
    }

    @RequestMapping(path = "/exercise", method = RequestMethod.GET)
    public List<Exercise> listExercises() {
        return exerciseDao.getExercise();
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @RequestMapping(path = "/exercise/{userId}", method = RequestMethod.GET)
    public List<Exercise> getExerciseLogByUserId(@PathVariable("userId") int userExerciseLogId) {
        List <Exercise> exercise = exerciseDao.getExerciseLogByUserId(userExerciseLogId);
        return exercise;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/submit-exercise-log", method = RequestMethod.POST)
    public Exercise submitExerciseLog(@Valid @RequestBody ExerciseLogDto exerciseLogDto){
        User currentUser = userDao.getUserByUsername(SecurityUtils.getCurrentUsername());

        Exercise newExerciseLog = new Exercise();

        newExerciseLog.setRecordedDate(exerciseLogDto.getRecordedDate());
        newExerciseLog.setResistance(exerciseLogDto.getResistance());
        newExerciseLog.setSets(exerciseLogDto.getSets());
        newExerciseLog.setRepetitions(exerciseLogDto.getRepetitions());
        newExerciseLog.setComments(exerciseLogDto.getComments());
        newExerciseLog.setUserId(currentUser.getId());

        ProgramExercise programExercise = programExerciseDao.getProgramExerciseByName(exerciseLogDto.getExerciseName());
        if(programExercise == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found.");
        }
        newExerciseLog.setExerciseId(programExercise.getId());

        try{
            Exercise submittedExercise = exerciseDao.submitExercise(newExerciseLog);
            return submittedExercise;
        }catch(DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    private ProgramExerciseDto mapProgramExerciseToProgramExerciseDto(ProgramExercise programExercise) {
        ProgramExerciseDto programExerciseDto = new ProgramExerciseDto();
        programExerciseDto.setId(programExercise.getId());
        programExerciseDto.setDayOfTheWeek(programExercise.getDayOfTheWeek());
        programExerciseDto.setExerciseName(programExercise.getExerciseName());
        programExerciseDto.setResistance(programExercise.getResistance());
        programExerciseDto.setSets(programExercise.getSets());
        programExerciseDto.setRepetitions(programExercise.getRepetitions());
        return programExerciseDto;
    }

}
