package name.backend.service;

import name.backend.Entities.UserEntity;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    void deleteUser(Integer id);

}
