package name.fapi.service;

import name.fapi.module.User;

public interface UserService {
    User save(User user);
    User findByLogin(String login);
    String getUsername(String login);
}
