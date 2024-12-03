package com.zerotoismail.fakestoreapi.models;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Category extends BaseModel{
    private String name;
}
