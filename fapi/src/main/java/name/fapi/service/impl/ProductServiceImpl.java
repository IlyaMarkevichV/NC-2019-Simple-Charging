package name.fapi.service.impl;

import name.fapi.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${backend.server.url}")
    private String backendServerUrl;
}
