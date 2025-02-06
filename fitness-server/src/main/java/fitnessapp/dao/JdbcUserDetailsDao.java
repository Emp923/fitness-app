package fitnessapp.dao;

import fitnessapp.exception.DaoException;
import fitnessapp.model.UserDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class JdbcUserDetailsDao implements UserDetailsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails getUserById(int userId) {
        UserDetails user = null;
        String sql = "SELECT user_id, preferred_name, availability, birthday, gender, restrictions, goals, comments" +
                " FROM user_details WHERE user_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
            if (results.next()) {
                user = mapRowToUserDetails(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return user;
    }

    @Override
    public UserDetails createUserDetails(UserDetails userDetails) {
        UserDetails newUserDetails = null;
        String sql = "INSERT INTO user_details ( " +
                " user_id, preferred_name, availability, birthday, gender, restrictions, goals, comments) " +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)" +
                " RETURNING user_id";

        try {
            int newUserDetailsId = jdbcTemplate.queryForObject(sql, int.class, userDetails.getUserId(), userDetails.getPreferredName(), userDetails.getAvailability(), Date.valueOf(userDetails.getBirthday()),
                    userDetails.getGender(), userDetails.getRestrictions(), userDetails.getGoals(), userDetails.getComments());

            newUserDetails = getUserById(newUserDetailsId);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server", e);
        } catch (DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }

        return newUserDetails;
    }

    private UserDetails mapRowToUserDetails(SqlRowSet rs){
        UserDetails userDetails = new UserDetails();
        userDetails.setUserId(rs.getInt("user_id"));
        userDetails.setPreferredName(rs.getString("preferred_name"));
        userDetails.setAvailability(rs.getString("availability"));
        userDetails.setBirthday(rs.getString("birthday"));
        userDetails.setGender(rs.getString("gender"));
        userDetails.setRestrictions(rs.getString("restrictions"));
        userDetails.setGoals(rs.getString("goals"));
        userDetails.setComments(rs.getString("comments"));
        return userDetails;
    }

}
