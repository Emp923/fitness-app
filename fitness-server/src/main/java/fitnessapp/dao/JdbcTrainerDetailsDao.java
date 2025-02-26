package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.TrainerDetails;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TransferQueue;

@Component
public class JdbcTrainerDetailsDao implements TrainerDetailsDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTrainerDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TrainerDetails> getTrainerDetails() {
        List<TrainerDetails> trainerDetails = new ArrayList<>();
        String sql = "SELECT trainer_id, name, bio, specialty_one, specialty_two, certification, photograph" +
                " FROM trainer_details ORDER BY name ASC";

        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                TrainerDetails trainerDetail = mapRowToTrainerDetails(results);
                trainerDetails.add(trainerDetail);
            }
        }catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database", e);
        }
        return trainerDetails;
    }

    @Override
    public TrainerDetails getTrainerById(int trainerId) {
        TrainerDetails trainer = null;
        String sql = "SELECT trainer_id, name, bio, specialty_one, specialty_two, certification, photograph" +
                " FROM trainer_details WHERE trainer_id = ?";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, trainerId);
            if (results.next()) {
                trainer = mapRowToTrainerDetails(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return trainer;
    }

    private TrainerDetails mapRowToTrainerDetails(SqlRowSet rs){
        TrainerDetails trainerDetails = new TrainerDetails();
        trainerDetails.setTrainerId(rs.getInt("trainer_id"));
        trainerDetails.setName(rs.getString("name"));
        trainerDetails.setBio(rs.getString("bio"));
        trainerDetails.setSpecialtyOne(rs.getString("specialty_one"));
        trainerDetails.setSpecialtyTwo(rs.getString("specialty_two"));
        trainerDetails.setCertification(rs.getString("certification"));
        trainerDetails.setPhotograph(rs.getString("photograph"));
        return trainerDetails;
    }

}
