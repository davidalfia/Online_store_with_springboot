package hac.ex4.repo;

import lombok.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message= "Name is mandatory")
    private String name;

    @NotEmpty(message= "Image is mandatory")
    private String image = "https://islandpress.org/books/modeling-environment";

    @Min(value = 0, message = "min value is > 0" )
    private int quantity;

    @Min(value = 0, message = "min value is > 0" )
    private  double price;

    @Min(value = 0, message = "min value is > 0" )
    private  double discount ;

    public Product() {}

    public Product(String name, String image,int quantity,double price,double discount) {
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", userName=" + name +  '}';
    }
}
