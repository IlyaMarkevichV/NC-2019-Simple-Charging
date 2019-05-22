package name.backend.service.Impl;

import name.backend.Entities.SubscriptionEntity;
import name.backend.repository.SubscriptionRepository;
import name.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionEntity save(SubscriptionEntity subscriptionEntity) {
        return subscriptionRepository.save(subscriptionEntity);
    }

    @Override
    public Page<SubscriptionEntity> getAll(Pageable pageable, String login) {
        return subscriptionRepository.findAllByWalletUserUserLogin(pageable, login);
    }

    @Override
    public void unsub(int id) {
        subscriptionRepository.deleteById(id);
    }
}
