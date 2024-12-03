package com.zerotoismail.fakestoreapi.dto;

import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.models.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    private long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;


    public Product toProduct(){
        Product product = new Product();
        product.setId(this.id);
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setImageUrl(this.image);
        product.setPrice(this.price);

        Category cate = new Category();
        cate.setName(this.category);

        product.setCategory(cate);

        return product;
    }
}
