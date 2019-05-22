package name.fapi.service.impl;

import name.fapi.service.RoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List getAll() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"/api/role/all", List.class).getBody();
    }
}
