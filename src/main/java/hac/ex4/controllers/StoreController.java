package hac.ex4.controllers;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Product;
import hac.ex4.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;


@Controller
public class StoreController {

    @Autowired
    private ProductRepository repository;
    private ProductRepository getRepo() {return repository;}

    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/")
    public String home(Model model, Product product) {
        model.addAttribute("products",getRepo().findAll());
        return "store";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") long id, Model model) {
        Product product = getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        shoppingCart.add(product);
        model.addAttribute("products",repository.findAll());
        return "store";

    }

    @GetMapping("/cart")
    public String cart(Model model, Product product){
        model.addAttribute("userProducts",shoppingCart.getShoppingCart());
        return "cart";
    }

}
