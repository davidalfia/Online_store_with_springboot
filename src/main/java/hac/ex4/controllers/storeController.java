package hac.ex4.controllers;

import hac.ex4.repo.Product;
import hac.ex4.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class storeController {

    @Autowired
    private ProductRepository repository;
    private ProductRepository getRepo() {return repository;}

    @GetMapping("/")
    public String home(Model model, Product product) {
        return "store"; //view
    }

    @GetMapping("/cart")
    public String cart(Model model, Product product){
        return "cart";
    }

}
