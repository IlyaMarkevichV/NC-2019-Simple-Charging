package name.fapi.controller;

import name.fapi.module.PageDTO;
import name.fapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping(value = "/products")
    public ResponseEntity<PageDTO> getProducts(@RequestParam(value = "id") int id,
                                               @RequestParam(value = "page") int page){
        return ResponseEntity.ok(categoryService.getProducts(id, page));
    }
}
