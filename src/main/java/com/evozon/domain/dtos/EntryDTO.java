package com.evozon.domain.dtos;

/**
 * Created by mateimihai on 7/22/2016.
 */
public class EntryDTO {

    private String name;

    private Double price;

    private Integer quantity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
