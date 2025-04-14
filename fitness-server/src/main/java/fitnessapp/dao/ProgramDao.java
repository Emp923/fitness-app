package fitnessapp.dao;

import fitnessapp.model.Program;
import fitnessapp.model.ProgramBasicDto;
import fitnessapp.model.ProgramCreateDto;
import fitnessapp.model.ProgramDetailDto;

import java.util.List;

public interface ProgramDao {

    List<ProgramBasicDto> listAllPrograms();

    ProgramDetailDto getProgramDetailById(int id);

    Program getProgramById(int id);

    Program createProgram(int trainerId, ProgramCreateDto programCreateDto);

}
