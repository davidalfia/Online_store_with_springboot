package hac.ex4.controllers;

import hac.ex4.Service.ProductService;
import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StoreController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCart shoppingCart;

    /**
     *
     * @return home page store with 5 best discount
     */
    @GetMapping("/")
    public String home(Model model, Product product) {
        model.addAttribute("products",productService.findTop5BestDiscount());
        model.addAttribute("shoppingCartSize",shoppingCart.size());
        return "store";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") long id, @RequestParam("quantity") int quantity,  Model model) {
        Product product = productService.getProduct(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        Product p = new Product(product.getName(),product.getImage(),quantity,product.getPrice(),product.getDiscount());
        p.setId(product.getId());
        shoppingCart.add(p);
        return "redirect:/";

    }

    /**
     *
     * @param keyword from search bar to search in stock
     * @return store page
     */
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("products",productService.getProductByNameLikeJPA(keyword));
        model.addAttribute("shoppingCartSize",shoppingCart.size());
        return "store";
    }

}
