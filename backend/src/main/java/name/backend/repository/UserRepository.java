package name.backend.repository;

import name.backend.Entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUserLogin(String login);
    List<UserEntity> findAllByRoleRoleIdAfter(int id);
}
