package name.backend.controller;

import name.backend.Entities.PageDTO;
import name.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<PageDTO> productsPage(@RequestParam(value = "page") int page,
                                                            @RequestParam(value = "size") int size,
                                                            @RequestParam(value = "dir") int direction){
        Sort sort = direction > 0 ? new Sort(Sort.Direction.DESC, "productName")
                : new Sort(Sort.Direction.ASC, "productName");
        Page p = productService.findProductsPage(new PageRequest(page, size, sort));
        PageDTO pageDTO = new PageDTO(p.getContent(), p.getTotalPages());
        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<PageDTO> searchProduct(@RequestParam(value = "product") String search,
                                                 @RequestParam(value = "page") int page,
                                                 @RequestParam(value = "size") int size,
                                                 @RequestParam(value = "dir") int direction){
        PageRequest pageRequest = new PageRequest(page, size, direction > 0 ? new Sort(Sort.Direction.DESC, "productName")
                :new Sort(Sort.Direction.ASC, "productName"));
        Page p = productService.searchProduct(pageRequest, search);
        PageDTO pageDTO = new PageDTO(p.getContent(), p.getTotalPages());
        return ResponseEntity.ok(pageDTO);
    }
}
