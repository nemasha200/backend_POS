package com.example.pos.category;

import com.example.pos.item.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping (path = "api/v1/category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController (CategoryService categoryService) {this.categoryService = categoryService;}

    @GetMapping
    public List<Category> getCategory() {return categoryService.getCategory();}

    @PostMapping
    public Map<String,String> addNewCategory(@RequestBody Category category) {
       return categoryService.addNewCategory(category);
    }



}
