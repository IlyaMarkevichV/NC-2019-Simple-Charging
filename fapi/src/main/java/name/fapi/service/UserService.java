package name.fapi.service;

import name.fapi.module.User;
import name.fapi.module.UserDTO;

import java.util.List;

public interface UserService {
    User save(User user);
    User findByLogin(String login);
    UserDTO getUserDTO(String login);
    List getUserSubs(String login);
    List<User> getAllUsers();
    void deleteUser(int id);
    Boolean check(String login);
}
