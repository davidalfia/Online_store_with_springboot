package hac.ex4.beans;

import hac.ex4.repo.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

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

    /**
     *
     * @param product to add, only if not in cart
     */
    public void add(Product product) {
        for(Product p : shoppingCart){
            if(product.getId()  == p.getId()){
                return;
            }
        }
        shoppingCart.add(product);
    }


    /**
     *
     * @param id delete product from cart by id
     */
    public void delete(long id){
        for(Product p : shoppingCart){
            if(p.getId() == id) {
                shoppingCart.remove(p);
                return;
            }
        }
    }

    /**
     *
     * @return shopping cart size
     */
    public int size() {
        return shoppingCart.size();
    }

    /**
     * empty cart
     */
    public void deleteAll(){
        shoppingCart.removeAll(getShoppingCart());
    }
}
