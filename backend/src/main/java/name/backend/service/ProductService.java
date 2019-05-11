package name.backend.service;

import name.backend.Entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductEntity> findProductsPage(Pageable pageable);
    Page<ProductEntity> searchProduct(Pageable pageable, String string);
}
