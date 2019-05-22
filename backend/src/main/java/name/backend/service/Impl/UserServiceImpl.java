package name.backend.service.Impl;

import name.backend.Entities.UserEntity;
import name.backend.repository.SubscriptionRepository;
import name.backend.repository.UserRepository;
import name.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public UserEntity saveUser(UserEntity user){
        return repository.save(user);}

    @Override
    public void deleteUser(Integer id){repository.deleteById(id);}

    @Override
    public UserEntity findByLogin(String login) {
        return repository.findByUserLogin(login);
    }

    @Override
    public List getUserSubs(String login) {
        return subscriptionRepository.findAllByWalletUserUserLogin(login);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return repository.findAllByRoleRoleIdAfter(1);
    }

    @Override
    public Boolean check(String login) {
        return repository.findByUserLogin(login) != null;
    }


}
