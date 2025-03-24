package fitnessapp.dao;

import fitnessapp.model.Program;
import fitnessapp.model.ProgramCreateDto;

import java.util.List;

public interface ProgramDao {

    List<Program> listAllPrograms();

    Program getProgramById(int id);

    Program createProgram(int trainerId, ProgramCreateDto programCreateDto);

}
