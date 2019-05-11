package name.backend.repository;

import name.backend.Entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
    //List<ProductEntity> findAllByProductNameStartsWith(String string);
    Page<ProductEntity> findAllByProductNameStartsWith(Pageable pageable, String string);
}
