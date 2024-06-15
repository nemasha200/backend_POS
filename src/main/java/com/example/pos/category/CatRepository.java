package com.example.pos.category;

import com.example.pos.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatRepository
    extends JpaRepository <Category, Long> {

    Optional<Category> findByCatName(String category_name);

}
