package fitnessapp.dao;

import fitnessapp.model.RegisterUserDto;
import fitnessapp.model.UpdateUserRoleDto;
import fitnessapp.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);

    void updateUserRole(int id, UpdateUserRoleDto userRoleDto);
}
