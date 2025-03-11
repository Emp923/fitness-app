package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.Program;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProgramDao implements ProgramDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcProgramDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Program> getProgram(){
        List<Program> program = new ArrayList<>();
        String sql = "SELECT id, day_of_the_week, exercise, resistance, sets, repetitions, user_id " +
                "FROM program_exercises ORDER BY exercise ASC";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Program programs = mapRowToProgram(results);
                program.add(programs);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }

        return program;
    }

    private Program mapRowToProgram(SqlRowSet rs){
        Program program = new Program();
        program.setId(rs.getInt("id"));
        program.setDayOfTheWeek(rs.getString("day_of_week"));
        program.setExercise(rs.getString("exercise"));
        program.setResistance(rs.getInt("resistance"));
        program.setSets(rs.getInt("sets"));
        program.setRepetitions(rs.getInt("repetitions"));
        program.setUserId(rs.getInt("user_id"));
        return program;
    }
}
