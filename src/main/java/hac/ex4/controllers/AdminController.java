package hac.ex4.controllers;


import hac.ex4.repo.Product;
import hac.ex4.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AdminController {

    @Autowired
    private ProductRepository repository;
    private ProductRepository getRepo() {return repository;}


    /**
     *
     * @param product
     * @return admin stock page
     */
    @GetMapping("/admin")
    public String main(Model model, Product product) {
        model.addAttribute("products",getRepo().findAll());
        return "admin-store"; //view
    }


    /**
     *
     * @return add product to stock page
     */
    @GetMapping("/admin/addform")
    public String addForm(Product product, BindingResult result, Model model){
            return "add-product";
    }

    /**
     *
     * @param id of product if to delete
     * @return admin stock page
     */
    @GetMapping("/admin/delete/{id}")
    public String deleteById(@PathVariable("id") long id, Model model) {
        Product product = getRepo()
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid user Id:" + id)
                );
        getRepo().delete(product);
        model.addAttribute("products", getRepo().findAll());
        return "admin-store";
    }

    /**
     *
     * @param product get entity product
     * @param result in order to display errors
     * @return admin stock page
     */
    @PostMapping("/admin/addProduct")
    public String postAddProduct(@Valid Product product, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "add-product";
        }
        getRepo().save(product);
        model.addAttribute("products",getRepo().findAll());
        return "admin-store";
    }
}
