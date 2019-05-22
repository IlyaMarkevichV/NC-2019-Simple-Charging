package name.backend.repository;

import name.backend.Entities.SubscriptionEntity;
import name.backend.Entities.WalletEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends PagingAndSortingRepository<SubscriptionEntity, Integer> {
    Page<SubscriptionEntity> findAllByWalletUserUserLogin(Pageable pageable, String login);
    List findAllByWalletUserUserLogin(String login);
    List<SubscriptionEntity> findAllByWalletWalletId(int id);
}
