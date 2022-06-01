package hac.ex4.controllers;


import hac.ex4.repo.Product;
import hac.ex4.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminController {

    private String message = "david";

    @Autowired
    private ProductRepository repository;
    private ProductRepository getRepo() {return repository;}

    @GetMapping("/")
    public String main(Model model,Product product) {
        model.addAttribute("message", message);
        return "index"; //view
    }

    @PostMapping("/addProduct")
    public String postAddProduct(@Valid Product product, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "index";
        }
    getRepo().save(product);
        return "index";
    }


}
