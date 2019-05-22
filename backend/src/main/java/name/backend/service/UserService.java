package name.backend.service;

import name.backend.Entities.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    void deleteUser(Integer id);
    UserEntity findByLogin(String login);
    List getUserSubs(String login);
    List<UserEntity> getAllUsers();
    Boolean check(String login);
}
