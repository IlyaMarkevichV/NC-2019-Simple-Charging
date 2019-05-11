package name.fapi.controller;

import name.fapi.module.PageDTO;
import name.fapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/page")
    public ResponseEntity<PageDTO> getPage(@RequestParam(value = "page") int page,
                                           @RequestParam(value = "size") int size,
                                           @RequestParam(value = "dir") int dir){

        return ResponseEntity.ok(productService.findAllProducts(page, size, dir));
    }

    @GetMapping("/search")
    public ResponseEntity<PageDTO> searchProduct(@RequestParam(value = "product") String search,
                                                       @RequestParam(value = "page") int page,
                                                       @RequestParam(value = "size") int size,
                                                       @RequestParam(value = "dir") int dir){
        return ResponseEntity.ok(productService.searchProduct(search, page, size, dir));
    }

}
