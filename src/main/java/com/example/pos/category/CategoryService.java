package com.example.pos.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {

    private final CatRepository catRepository;

    public CategoryService (CatRepository catRepository) {this.catRepository = catRepository;}

    public List<Category> getCategory() {return catRepository.findAll();}

    public Map<String,String> addNewCategory(Category category) {
        Optional<Category> categoryOptional = catRepository
                .findByCatName(category.getCatName());
        if (categoryOptional.isPresent()){
            return Map.of(
                    "status", "error",
                    "message", "Name taken"
            );
        }
         catRepository.save(category);
        return Map.of(
                "status", "success");
    }
}
