package name.fapi.service;

import name.fapi.module.PageDTO;

public interface ProductService {
    PageDTO findAllProducts(int p, int s, int d);
    PageDTO searchProduct(String search, int p, int s, int d);
}
