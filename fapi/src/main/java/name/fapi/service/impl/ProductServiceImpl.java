package name.fapi.service.impl;

import name.fapi.module.PageDTO;
import name.fapi.module.Product;
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
        return restTemplate.getForEntity(backendServerUrl + "/api/products/search?product={string}&page={page}&size={size}&dir={dir}", PageDTO.class, search, p, s, d).getBody();
    }

    @Override
    public Product saveProduct(Product product) {
        RestTemplate restTemplate=new RestTemplate();
        return  restTemplate.postForEntity(backendServerUrl + "/api/products/save", product, Product.class).getBody();
    }

    @Override
    public void deleteProduct(int id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl+"/api/products/delete?id={id}", id);
    }

    @Override
    public Product getProductById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(backendServerUrl+"/api/products/get?id={id}", Product.class, id).getBody();
    }
}
