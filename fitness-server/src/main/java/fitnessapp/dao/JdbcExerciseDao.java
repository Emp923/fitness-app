package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.Exercise;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcExerciseDao implements ExerciseDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcExerciseDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Exercise> getExercise(){
        List<Exercise> exercise = new ArrayList<>();
        String sql = "SELECT id, recorded_date, resistance, sets, repetitions, exercise_id, comments " +
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

    @Override
    public Exercise getExerciseById(int id){
        Exercise exercise = null;
        String sql = "SELECT id, recorded_date, resistance, sets, repetitions, exercise_id, comments " +
                "FROM exercise_log WHERE id = ?";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()){
                exercise = mapRowToExercise(results);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return exercise;
    }

    @Override
    public Exercise submitExercise(Exercise exercise){
        Exercise newExercise = null;
        String sql = "INSERT INTO exercise_log " +
                "(recorded_date, resistance, sets, repetitions, exercise_id, comments) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "RETURNING id";

        try{
            int newExerciseId = jdbcTemplate.queryForObject(sql, int.class, Date.valueOf(exercise.getRecordedDate()), exercise.getResistance(),
                    exercise.getSets(), exercise.getRepetitions(), exercise.getExerciseId(), exercise.getComments());
            newExercise = getExerciseById(newExerciseId);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return newExercise;
    }

    private Exercise mapRowToExercise(SqlRowSet rs){
        Exercise exercise = new Exercise();
        exercise.setId(rs.getInt("id"));
        exercise.setRecordedDate(rs.getString("recorded_date"));
        exercise.setResistance(rs.getInt("resistance"));
        exercise.setSets(rs.getInt("sets"));
        exercise.setRepetitions(rs.getInt("repetitions"));
        exercise.setExerciseId(rs.getInt("exercise_id"));
        exercise.setComments(rs.getString("comments"));
        return exercise;
    }
}
