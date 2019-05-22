package name.fapi.service.impl;

import name.fapi.module.PageDTO;
import name.fapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List getCategories() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"/api/category/all", List.class).getBody();
    }

    @Override
    public PageDTO getProducts(int id, int page) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"/api/category/products?id={id}&page={page}", PageDTO.class, id, page).getBody();
    }
}
