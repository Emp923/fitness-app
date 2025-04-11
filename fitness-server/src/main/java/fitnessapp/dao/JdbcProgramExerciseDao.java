package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.ProgramExercise;
import fitnessapp.model.ProgramExerciseDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProgramExerciseDao implements ProgramExerciseDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProgramExerciseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProgramExercise> getProgramExercises() {
        List<ProgramExercise> programs = new ArrayList<>();
        String sql = "SELECT id, day_of_the_week, exercise_name, resistance, sets, repetitions " +
                "FROM program_exercises ORDER BY exercise_name ASC";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                ProgramExercise program = mapRowToProgram(results);
                programs.add(program);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return programs;
    }

    @Override
    public ProgramExercise getProgramExerciseById(int programExerciseId) {
        ProgramExercise programExercise = null;
        String sql = "SELECT id, day_of_the_week, exercise_name, resistance, sets, repetitions " +
                "FROM program_exercises WHERE id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, programExerciseId);
            while (results.next()) {
                programExercise = mapRowToProgram(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return programExercise;
    }

    @Override
    public ProgramExercise getProgramExerciseByName(String exerciseName){
        ProgramExercise programExercise = null;
        String sql = "SELECT id, day_of_the_week, exercise_name, resistance, sets, repetitions " +
                "FROM program_exercises WHERE exercise_name = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, exerciseName);
            while (results.next()) {
                programExercise = mapRowToProgram(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return programExercise;
    }

    @Override
    public List<ProgramExercise> getProgramExercisesByProgramId(int programId) {
        List<ProgramExercise> programExercises = new ArrayList<>();
        String sql = "SELECT program_exercises.id, program_exercises.day_of_the_week, program_exercises.exercise_name," +
                "program_exercises.resistance, program_exercises.sets, program_exercises.repetitions " +
                "FROM program_exercises " +
                "JOIN programs_program_exercises ON program_exercises.id = programs_program_exercises.program_exercise_id " +
                "JOIN programs ON programs_program_exercises.program_id = programs.id " +
                "WHERE programs.id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, programId);
            while (results.next()) {
                ProgramExercise programExercise = mapRowToProgram(results);
                programExercises.add(programExercise);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return programExercises;
    }

    @Override
    public ProgramExercise createProgramExercise(ProgramExerciseDto programExerciseDto) {
        ProgramExercise newProgramExercise;
        String insertSql = "INSERT INTO program_exercises " +
                "(day_of_the_week, exercise_name, resistance, sets, repetitions) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING id";

        try {
            int newProgramExerciseId = jdbcTemplate.queryForObject(insertSql, int.class,
                    programExerciseDto.getDayOfTheWeek(), programExerciseDto.getExerciseName(),
                    programExerciseDto.getResistance(), programExerciseDto.getSets(),
                    programExerciseDto.getRepetitions());
            newProgramExercise = getProgramExerciseById(newProgramExerciseId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newProgramExercise;
    }

    @Override
    public void assignProgramExerciseToProgram(int programExerciseId, int programId) {
        String insertSql = "INSERT INTO programs_program_exercises (program_id, program_exercise_id) VALUES (?, ?)";

        try {
//            jdbcTemplate.queryForObject(insertSql, int.class, programId, programExerciseId);
            jdbcTemplate.update(insertSql, programId, programExerciseId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    private ProgramExercise mapRowToProgram(SqlRowSet rs) {
        ProgramExercise program = new ProgramExercise();
        program.setId(rs.getInt("id"));
        program.setDayOfTheWeek(rs.getString("day_of_the_week"));
        program.setExerciseName(rs.getString("exercise_name"));
        program.setResistance(rs.getInt("resistance"));
        program.setSets(rs.getInt("sets"));
        program.setRepetitions(rs.getInt("repetitions"));
        return program;
    }
}
