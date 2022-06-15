package hac.ex4.beans;

import hac.ex4.repo.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;

@Component
@Getter
@Setter
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private ArrayList<Product> shoppingCart;

    public ShoppingCart() {
        this.shoppingCart = new ArrayList<>();
    }

    boolean in(Product product) {
        if(shoppingCart.contains(product))
            System.out.println("in");
        return false;
    }

    public void add(Product product) {
        shoppingCart.add(product);
    }

}
