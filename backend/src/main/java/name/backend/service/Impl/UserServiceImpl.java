package name.backend.service.Impl;

import name.backend.Entities.UserEntity;
import name.backend.repository.UserRepository;
import name.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity saveUser(UserEntity user){return repository.save(user);}

    @Override
    public void deleteUser(Integer id){repository.deleteById(id);}

    @Override
    public UserEntity findByLogin(String login) {
        return repository.findByUserLogin(login);
    }

    @Override
    public UserEntity findUsername(String login) {
        return repository.findByUserLogin(login);
    }


}
