package name.fapi.service.impl;

import name.fapi.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Value("${backend.server.url}")
    private String backendServerUrl;
}
