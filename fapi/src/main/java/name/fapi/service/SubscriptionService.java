package name.fapi.service;

import name.fapi.module.PageDTO;
import name.fapi.module.Subscription;

public interface SubscriptionService {
    Subscription saveSubs(Subscription subscription);
    PageDTO getAllSubs(int p, String login);
    void unsub(int id);
}
