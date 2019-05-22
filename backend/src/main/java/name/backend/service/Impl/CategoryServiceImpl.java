package name.backend.service.Impl;

import name.backend.Entities.ProductEntity;
import name.backend.repository.CategoryRepository;
import name.backend.repository.ProductRepository;
import name.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<ProductEntity> getProducts(Pageable pageable, int id) {
        return productRepository.findAllByCategoryCategoryId(pageable, id);
    }
}
