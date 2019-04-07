package name.fapi.service.impl;

import name.fapi.service.RoleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Value("${backend.server.url}")
    private String backendServerUrl;
}
