package name.backend.controller;

import name.backend.Entities.PageDTO;
import name.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/all")
    public ResponseEntity<List> getCategories(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping(value = "/products")
    public ResponseEntity<PageDTO> getProducts(@RequestParam(value = "id") int id,
                                               @RequestParam(value = "page") int page){
        Page p=categoryService.getProducts(new PageRequest(page, 6, new Sort(Sort.Direction.ASC, "productName")), id);
     return ResponseEntity.ok(new PageDTO(p.getContent(), p.getTotalPages()));
    }
}
