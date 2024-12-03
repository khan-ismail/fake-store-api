package com.zerotoismail.fakestoreapi.repositories.categories;

import com.zerotoismail.fakestoreapi.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    boolean existsCategoryByName(String name);
    boolean existsCategoryByNameNotIgnoreCase(String name);
    List<Category> findByNameContaining(String name);
    List<Category> findCategoryByNameEndingWith(String name);
}
