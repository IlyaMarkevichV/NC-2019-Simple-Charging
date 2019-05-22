package name.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List getAll();
    Page getProducts(Pageable pageable, int id);
}
