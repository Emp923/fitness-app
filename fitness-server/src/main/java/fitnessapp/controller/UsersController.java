package fitnessapp.controller;

import fitnessapp.dao.UserDao;
import fitnessapp.exception.DaoException;
import fitnessapp.model.UpdateUserRoleDto;
import fitnessapp.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/users")
public class UsersController {

    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    private final UserDao userDao;

    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return this.userDao.getUsers();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        User user;
        try {
            user = this.userDao.getUserById(id);
        } catch (DaoException e) {
            LOG.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id: %s was not found", id));
        }
        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}/update-role", method = RequestMethod.PATCH)
    public void updateUserRole(@PathVariable int id, @RequestBody UpdateUserRoleDto userRoleDto) {
        if (userRoleDto.getRole() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "required field: 'role' cannot be null");
        }
        User user = this.getUserById(id);
        this.userDao.updateUserRole(id, userRoleDto);
    }

}
