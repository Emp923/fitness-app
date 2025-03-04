package fitnessapp.controller;

import fitnessapp.dao.UserDetailsDao;
import fitnessapp.exception.DaoException;
import fitnessapp.model.UserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping(path = "/user-details")
public class UserDetailsController {

    private final UserDetailsDao userDetailsDao;

    public UserDetailsController(UserDetailsDao userDetailsDao){
        this.userDetailsDao = userDetailsDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public UserDetails createUserDetails(@Valid @RequestBody UserDetails userDetails) {

        try{
            UserDetails createdUserDetails = userDetailsDao.createUserDetails(userDetails);
            return createdUserDetails;
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
