package name.fapi.service.impl;

import name.fapi.module.PageDTO;
import name.fapi.service.ProductService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public PageDTO findAllProducts(int p, int s, int d) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl + "/api/products/all?page={page}&size={size}&dir={dir}", PageDTO.class, p, s, d).getBody();
    }

    @Override
    public PageDTO searchProduct(String search, int p, int s, int d) {
        RestTemplate restTemplate=new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl + "api/products/search?product={string}&page={page}&size={size}&dir={dir}", PageDTO.class, search, p, s, d).getBody();
    }
}
