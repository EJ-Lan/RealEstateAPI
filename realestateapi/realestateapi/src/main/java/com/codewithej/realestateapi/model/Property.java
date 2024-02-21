package com.codewithej.realestateapi.model;


import jakarta.persistence.*;


@Entity
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private Double price;
    @Enumerated(EnumType.STRING)
    private PropertyType type;
    private Integer bedrooms;
    private Integer bathrooms;

    public Property() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }
}
