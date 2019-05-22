package name.backend.repository;

import name.backend.Entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
    Page<ProductEntity> findAllByProductNameStartsWith(Pageable pageable, String string);
    Page<ProductEntity> findAllByCategoryCategoryId(Pageable pageable, int id);
}
