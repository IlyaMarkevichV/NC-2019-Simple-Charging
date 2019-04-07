package name.fapi.service.impl;

import name.fapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

}
