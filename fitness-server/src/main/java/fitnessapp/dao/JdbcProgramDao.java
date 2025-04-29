package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.Program;
import fitnessapp.model.ProgramBasicDto;
import fitnessapp.model.ProgramCreateDto;
import fitnessapp.model.ProgramDetailDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProgramDao implements ProgramDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProgramDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProgramBasicDto> listAllPrograms() {
        List<ProgramBasicDto> programs = new ArrayList<>();
        String sql = "SELECT programs.id, programs.name, trainer_details.name AS created_by FROM programs " +
                "JOIN trainer_details ON programs.created_by = trainer_details.user_id ORDER BY programs.name";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                ProgramBasicDto program = mapRowToProgramBasicDto(results);
                programs.add(program);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return programs;
    }

    @Override
    public List<ProgramBasicDto> getProgramsByUserId(int userId) {
        List<ProgramBasicDto> programs = new ArrayList<>();
        String sql = "SELECT programs.id, programs.name, trainer_details.name AS created_by FROM programs " +
                "JOIN trainer_details ON programs.created_by = trainer_details.user_id " +
                "JOIN programs_users ON programs.id = programs_users.program_id " +
                "WHERE programs_users.user_id = ? " +
                "ORDER BY programs.name";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            while (results.next()) {
                ProgramBasicDto program = mapRowToProgramBasicDto(results);
                programs.add(program);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return programs;
    }

    @Override
    public Program getProgramById(int id) {
        Program program = null;
        String sql = "SELECT id, name, created_by FROM programs WHERE id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                program = mapRowToProgram(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return program;
    }

    @Override
    public ProgramDetailDto getProgramDetailById(int id) {
        ProgramDetailDto program = null;
        String sql = "SELECT programs.id, programs.name, trainer_details.name AS created_by FROM programs " +
                "JOIN trainer_details ON programs.created_by = trainer_details.user_id WHERE programs.id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                program = mapRowToProgramDetailDto(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return program;
    }

    @Override
    public Program createProgram(int trainerId, ProgramCreateDto programCreateDto) {
        Program newProgram;
        String insertSql = "INSERT INTO programs (name, created_by) VALUES (LOWER(TRIM(?)), ?) RETURNING id";

        try {
            int newProgramId = jdbcTemplate.queryForObject(insertSql, int.class, programCreateDto.getName(), trainerId);
            newProgram = getProgramById(newProgramId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newProgram;
    }

    @Override
    public void assignProgramToUser(int programId, int userId) {
        String insertSql = "INSERT INTO programs_users (user_id, program_id) VALUES (?, ?)";

        try {
            jdbcTemplate.update(insertSql, userId, programId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public boolean isProgramAssignedToUser(int programId, int userId) {
        String sql = "SELECT user_id, program_id " +
                "FROM programs_users " +
                "WHERE user_id = ? AND program_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, programId);
            return results.next();
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    private Program mapRowToProgram(SqlRowSet rowSet) {
        Program program = new Program();
        program.setId(rowSet.getInt("id"));
        program.setName(rowSet.getString("name"));
        program.setCreatedBy(rowSet.getInt("created_by"));
        return program;
    }

    private ProgramBasicDto mapRowToProgramBasicDto(SqlRowSet rowSet) {
        ProgramBasicDto program = new ProgramBasicDto();
        program.setId(rowSet.getInt("id"));
        program.setName(rowSet.getString("name"));
        program.setCreatedBy(rowSet.getString("created_by"));
        return program;
    }

    private ProgramDetailDto mapRowToProgramDetailDto(SqlRowSet rowSet) {
        ProgramDetailDto program = new ProgramDetailDto();
        program.setId(rowSet.getInt("id"));
        program.setName(rowSet.getString("name"));
        program.setCreatedBy(rowSet.getString("created_by"));
        return program;
    }

}
