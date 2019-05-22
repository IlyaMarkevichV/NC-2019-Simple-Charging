package name.backend.repository;

import name.backend.Entities.WalletEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends PagingAndSortingRepository<WalletEntity, Integer> {
    Page<WalletEntity> findAllByUserUserLogin(Pageable pageable, String login);
    List<WalletEntity> findAllByUserUserLogin(String login);
    List<WalletEntity> findAllByUserRoleRoleId(int id);
}
