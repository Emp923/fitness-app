package fitnessapp.controller;

import fitnessapp.dao.UserDetailsDao;
import fitnessapp.exception.DaoException;
import fitnessapp.model.UserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public void createUserDetails(@Valid @RequestBody UserDetails userDetails) {

        try{
            UserDetails createdUserDetails = userDetailsDao.createUserDetails(userDetails);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
