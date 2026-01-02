package com.ecom.Project.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "categories")
public class category {
    @Id
    private Long categoryId;
    private String categoryname;

    public category(String categoryname, long categoryId) {
        this.categoryId = categoryId;
        this.categoryname = categoryname;

    }

    public category() {

    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
}


