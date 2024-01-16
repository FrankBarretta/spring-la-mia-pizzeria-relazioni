package com.learning.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Entity
@Table(name = "pizzas")
public class Pizza {


    // Attributi

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Size(min = 5, max = 50)
    @NotEmpty(message = "Name must not be empty!")
    private String name;

    @Lob
    @Column(nullable = false)
    @Size(min = 10, max = 200
    )
    @NotEmpty(message = "Description must not be empty!")
    private String description;

    @Lob
    private String imgUrl;


    @Column(nullable = false)
    @DecimalMin(value = "1.00", message = "Price can't be lower than 1.00")
    @NotNull(message = "Price must not be empty")
    private BigDecimal price;




    // Metodi

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
