package name.fapi.service;

import name.fapi.module.PageDTO;

import java.util.List;

public interface CategoryService {
    List getCategories();
    PageDTO getProducts(int id, int page);
}
