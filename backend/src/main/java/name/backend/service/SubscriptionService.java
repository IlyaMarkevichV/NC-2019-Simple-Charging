package name.backend.service;

import name.backend.Entities.PageDTO;
import name.backend.Entities.SubscriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {
    SubscriptionEntity save(SubscriptionEntity subscriptionEntity);
    Page<SubscriptionEntity> getAll(Pageable pageable, String login);
    void unsub(int id);
}
