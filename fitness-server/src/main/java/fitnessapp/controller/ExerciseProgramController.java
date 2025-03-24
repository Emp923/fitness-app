package fitnessapp.controller;

import fitnessapp.dao.ExerciseDao;
import fitnessapp.dao.ProgramDao;
import fitnessapp.dao.ProgramExerciseDao;
import fitnessapp.dao.UserDao;
import fitnessapp.exception.DaoException;
import fitnessapp.model.Exercise;
import fitnessapp.model.Program;
import fitnessapp.model.ProgramBasicDto;
import fitnessapp.model.ProgramCreateDto;
import fitnessapp.model.ProgramDetailDto;
import fitnessapp.model.ProgramExercise;
import fitnessapp.model.ProgramExerciseDto;
import fitnessapp.model.User;
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
        List<ProgramBasicDto> programBasicList = new ArrayList<>();
        for (Program program : programDao.listAllPrograms()) {
            programBasicList.add(mapProgramToProgramBasicDto(program));
        }
        return programBasicList;
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ProgramBasicDto createNewProgram(@RequestBody ProgramCreateDto programCreateDto) {
        if (programCreateDto == null || programCreateDto.getName() == null || programCreateDto.getName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Required field 'name' is missing or blank");
        }

        User currentTrainer = userDao.getUserByUsername(SecurityUtils.getCurrentUsername());

        ProgramBasicDto newProgramDto = null;

        try {
            Program newProgram = programDao.createProgram(currentTrainer.getId(), programCreateDto);
            newProgramDto = mapProgramToProgramBasicDto(newProgram);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return newProgramDto;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ProgramDetailDto getProgramById(@PathVariable("id") int programId) {
        Program program = programDao.getProgramById(programId);

        if (program == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("program with id: %s was not found", programId));
        }

        ProgramDetailDto programDetailDto = mapProgramToProgramDetailDto(program);

        List<ProgramExercise> programExercises = programExerciseDao.getProgramExercisesByProgramId(programId);
        List<ProgramExerciseDto> programExerciseDtos = new ArrayList<>();
        for (ProgramExercise programExercise : programExercises) {
            programExerciseDtos.add(mapProgramExerciseToProgramExerciseDto(programExercise));
        }
        programDetailDto.setProgramExercises(programExerciseDtos);

        return programDetailDto;
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{programId}/assign-program-exercise/{programExerciseId}", method = RequestMethod.POST)
    public void assignProgramExerciseToProgram(@PathVariable("programId") int programId,
                                               @PathVariable("programExerciseId") int programExerciseId) {

        this.getProgramById(programId);
        this.getProgramExerciseById(programExerciseId);

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

    private ProgramBasicDto mapProgramToProgramBasicDto(Program program) {
        ProgramBasicDto programBasicDto = new ProgramBasicDto();
        programBasicDto.setId(program.getId());
        programBasicDto.setName(program.getName());
        programBasicDto.setCreatedBy(program.getCreatedBy());
        return programBasicDto;
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

    private ProgramDetailDto mapProgramToProgramDetailDto(Program program) {
        ProgramDetailDto programDetailDto = new ProgramDetailDto();
        programDetailDto.setId(program.getId());
        programDetailDto.setName(program.getName());
        programDetailDto.setCreatedBy(program.getCreatedBy());
        return programDetailDto;
    }
}
