package name.backend.service.Impl;

import name.backend.Entities.ProductEntity;
import name.backend.repository.ProductRepository;
import name.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;


    @Override
    public Page<ProductEntity> findProductsPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<ProductEntity> searchProduct(Pageable pageable, String string) {
        return productRepository.findAllByProductNameStartsWith(pageable, string);
    }
}
