package fitnessapp.dao;

import fitnessapp.model.Program;
import fitnessapp.model.ProgramBasicDto;
import fitnessapp.model.ProgramCreateDto;
import fitnessapp.model.ProgramDetailDto;

import java.util.List;

public interface ProgramDao {

    List<ProgramBasicDto> listAllPrograms();

    List<ProgramBasicDto> getProgramsByUserId(int userId);

    ProgramDetailDto getProgramDetailById(int id);

    Program getProgramById(int id);

    Program createProgram(int trainerId, ProgramCreateDto programCreateDto);

    void assignProgramToUser(int programId, int userId);

    boolean isProgramAssignedToUser(int programId, int userId);
}
