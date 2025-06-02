package fitnessapp.controller;

import fitnessapp.dao.UserDao;
import fitnessapp.dao.UserDetailsDao;
import fitnessapp.exception.DaoException;
import fitnessapp.model.User;
import fitnessapp.model.UserDetails;
import fitnessapp.security.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/user-details")
public class UserDetailsController {

    private final UserDetailsDao userDetailsDao;
    private final UserDao userDao;

    public UserDetailsController(UserDetailsDao userDetailsDao, UserDao userDao){
        this.userDetailsDao = userDetailsDao;
        this.userDao = userDao;
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<UserDetails> listUserDetails() {
        return userDetailsDao.getUserDetails();
    }

    @PreAuthorize("hasRole('ROLE_TRAINER')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public UserDetails getUserDetailsById(@PathVariable("id") int userDetailsId) {
        UserDetails userDetails = userDetailsDao.getUserById(userDetailsId);

        return userDetails;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(path = "/my-details", method = RequestMethod.GET)
    public UserDetails getMyUserDetails(){
        User currentUser = userDao.getUserByUsername(SecurityUtils.getCurrentUsername());
        return getUserDetailsById(currentUser.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public UserDetails createUserDetails(@Valid @RequestBody UserDetails userDetails) {
        User currentUser = userDao.getUserByUsername(SecurityUtils.getCurrentUsername());

        userDetails.setUserId(currentUser.getId());

        try{
            UserDetails createdUserDetails = userDetailsDao.createUserDetails(userDetails);
            return createdUserDetails;
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }

}
