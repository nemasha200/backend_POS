package com.example.pos.category;

import jakarta.persistence.*;

@Entity
@Table
public class Category {


    @Id
    @SequenceGenerator(
            name = "category_sequence",
            sequenceName = "category_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"
    )

    private Long id;
    private String catName;

    private String cat_description;

    public Category(Long id,
                    String catName,

                    String cat_description) {
        this.id = id;
        this.catName = catName;

        this.cat_description = cat_description;
    }
    public Category(String catName, String cat_description) {
        this.catName = catName;

        this.cat_description = cat_description;
    }

    public Category() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String cat_name) {
        this.catName = cat_name;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }


}
