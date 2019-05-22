package name.fapi.controller;

import name.fapi.module.PageDTO;
import name.fapi.module.Product;
import name.fapi.module.User;
import name.fapi.security.TokenProvider;
import name.fapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private TokenProvider tokenProvider;

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

    @PreAuthorize("hasRole('ROLE_company')")
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product,
                                               @RequestHeader("Authorization") String token){
        token = token.substring(token.indexOf(" ")+1);
        String login = tokenProvider.getUsernameFromToken(token);
        product.setUser(new User());
        product.getUser().setUserLogin(login);
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_company')")
    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam(value = "id") int id){
        productService.deleteProduct(id);
    }


    @GetMapping("/get")
    public ResponseEntity<Product> getProduct(@RequestParam(value = "id") int id){
        return ResponseEntity.ok(productService.getProductById(id));
    }


}
