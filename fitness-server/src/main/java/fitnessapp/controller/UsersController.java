package fitnessapp.controller;

import fitnessapp.dao.UserDao;
import fitnessapp.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "/users")
public class UsersController {

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
        User user = this.userDao.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id: %s was not found", id));
        }
        return user;
    }

}
