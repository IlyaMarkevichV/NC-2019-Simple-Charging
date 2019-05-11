package name.backend.repository;

import name.backend.Entities.WalletEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends PagingAndSortingRepository<WalletEntity, Integer> {
    List<WalletEntity> findAllByUserUserId(int id);
}
