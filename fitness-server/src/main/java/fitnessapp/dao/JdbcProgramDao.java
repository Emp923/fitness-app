package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.Program;
import fitnessapp.model.ProgramCreateDto;
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

    public List<Program> listAllPrograms() {
        List<Program> programs = new ArrayList<>();
        String sql = "SELECT id, name, created_by FROM programs ORDER BY name";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Program program = mapRowToProgram(results);
                programs.add(program);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return programs;
    }

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

    private Program mapRowToProgram(SqlRowSet rowSet) {
        Program program = new Program();
        program.setId(rowSet.getInt("id"));
        program.setName(rowSet.getString("name"));
        program.setCreatedBy(rowSet.getInt("created_by"));
        return program;
    }

}
