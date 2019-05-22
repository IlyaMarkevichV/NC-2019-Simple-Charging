package name.fapi.service.impl;

import name.fapi.module.PageDTO;
import name.fapi.module.Subscription;
import name.fapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Subscription saveSubs(Subscription subscription) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl+"/api/subs/save", subscription, Subscription.class).getBody();
    }

    @Override
    public PageDTO getAllSubs(int p, String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"api/subs/all?page={page}&login={login}", PageDTO.class, p, login).getBody();
    }

    @Override
    public void unsub(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl+"/api/subs/unsub?id={id}", id);
    }
}
