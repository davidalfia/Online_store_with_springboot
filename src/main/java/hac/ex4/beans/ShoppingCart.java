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

    public void add(Product product) {
        for(Product p : shoppingCart){
            if(product.getId()  == p.getId()){
                return;
            }
        }
        shoppingCart.add(product);
    }

    public void delete(long id){
        for(Product p : shoppingCart){
            if(p.getId() == id) {
                shoppingCart.remove(p);
                return;
            }
        }
    }

    public int size() {
        return shoppingCart.size();
    }

    public double finalPrice() {
        double total = 0, price, discount, priceAfterDiscount = 0;

        for(Product p : shoppingCart) {
            price = p.getPrice();
            discount = p.getDiscount();
            priceAfterDiscount = price-(price*discount/100);
            total += priceAfterDiscount;
        }
        return total;
    }
}
