package hac.ex4.Service;

import hac.ex4.repo.Product;
import hac.ex4.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    private ProductRepository repository;

    public void addProducts(ArrayList<Product> products){repository.saveAll(products);}
    public void saveProduct(Product product) {repository.save(product);}
    public void deleteProduct(Product product) {repository.delete(product);}
    public Optional<Product> getProduct(Long id) {return repository.findById(id);}
    public List<Product> getProducts() {return repository.findAll();}



}
