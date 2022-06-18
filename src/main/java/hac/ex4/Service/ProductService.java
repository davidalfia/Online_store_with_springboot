package hac.ex4.Service;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Product;
import hac.ex4.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public void addProducts(ArrayList<Product> products){repository.saveAll(products);}
    public void saveProduct(Product product) {repository.save(product);}
    public void deleteProduct(Product product) {repository.delete(product);}
    public Optional<Product> getProduct(Long id) {return repository.findById(id);}
    public List<Product> getProducts() {return repository.findAll();}
    public List<Product> getProductByNameLikeJPA(String name){
        if(name!=null)
            return repository.getProductByNameLikeJPA(name);
        else
            return repository.findAll();
    }
    public List<Product> findTop5BestDiscount(){ return repository.findTop5ByOrderByDiscountDesc();}

    @Transactional
    public void checkout(ShoppingCart shoppingCart) {
        for (Product p : shoppingCart.getShoppingCart()) {
            Product product = repository.findById(p.getId()).orElseThrow(() -> new IllegalArgumentException("wrong id "));
            product.setQuantity(product.getQuantity() - p.getQuantity());
            if (product.getQuantity() == 0) {
                repository.delete(product);
            } else if (product.getQuantity() < 0) {
                throw new IllegalArgumentException("product " + p.getName() + "is out of stock, purchase has canceled. you have not been charged");
            }
        }
    }

}
