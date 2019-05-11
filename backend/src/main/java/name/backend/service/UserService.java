package name.backend.service;

import name.backend.Entities.UserEntity;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    void deleteUser(Integer id);
    UserEntity findByLogin(String login);
    UserEntity findUsername(String login);
}
