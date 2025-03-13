package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.ProgramExercise;
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
        List<ProgramExercise> program = new ArrayList<>();
        String sql = "SELECT id, day_of_the_week, exercise_name, resistance, sets, repetitions " +
                "FROM program_exercises ORDER BY exercise_name ASC";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                ProgramExercise programs = mapRowToProgram(results);
                program.add(programs);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return program;
    }

    private ProgramExercise mapRowToProgram(SqlRowSet rs) {
        ProgramExercise program = new ProgramExercise();
        program.setId(rs.getInt("id"));
        program.setDayOfTheWeek(rs.getString("day_of_week"));
        program.setExerciseName(rs.getString("exercise_name"));
        program.setResistance(rs.getInt("resistance"));
        program.setSets(rs.getInt("sets"));
        program.setRepetitions(rs.getInt("repetitions"));
        return program;
    }
}
