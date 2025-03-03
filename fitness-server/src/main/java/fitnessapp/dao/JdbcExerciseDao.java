package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.Exercise;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcExerciseDao implements ExerciseDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcExerciseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Exercise> getExercise(){
        List<Exercise> exercise = new ArrayList<>();
        String sql = "SELECT id, recorded_date, resistance, sets, repetitions, exercise_id " +
                "FROM exercise_log ORDER BY exercise_id ASC";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Exercise exercises = mapRowToExercise(results);
                exercise.add(exercises);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }

        return exercise;
    }

    private Exercise mapRowToExercise(SqlRowSet rs){
        Exercise exercise = new Exercise();
        exercise.setId(rs.getInt("id"));
        exercise.setRecordedDate(rs.getString("recorded_date"));
        exercise.setResistance(rs.getInt("resistance"));
        exercise.setSets(rs.getInt("sets"));
        exercise.setRepetitions(rs.getInt("repetitions"));
        exercise.setExerciseId(rs.getInt("exercise_id"));
        return exercise;
    }
}
