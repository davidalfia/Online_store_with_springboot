package hac.ex4.controllers;

import hac.ex4.Service.productService;
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
    private productService productService;

    @Autowired
    private ShoppingCart shoppingCart;

    @GetMapping("/")
    public String home(Model model, Product product) {
        model.addAttribute("products",productService.getProducts());
        model.addAttribute("shoppingCartSize",shoppingCart.size());
        return "store";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") long id, Model model) {
        Product product = productService.getProduct(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        shoppingCart.add(product);
        model.addAttribute("products",productService.getProducts());
        model.addAttribute("shoppingCartSize",shoppingCart.size());
        return "store";

    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("products",productService.getProductByNameLikeJPA(keyword));
        return "store";
    }

    @GetMapping("/cart")
    public String cart(Model model, Product product){
        model.addAttribute("userProducts",shoppingCart.getShoppingCart());
        model.addAttribute("shoppingCartSize",shoppingCart.size());
        model.addAttribute("shoppingCartTotal",shoppingCart.finalPrice());
        return "cart";
    }

    @PostMapping ("/cart/delete")
    public String cart(@RequestParam("id") long id, Model model){
        shoppingCart.delete(id);
        model.addAttribute("userProducts",shoppingCart.getShoppingCart());
        model.addAttribute("shoppingCartSize",shoppingCart.size());
        model.addAttribute("shoppingCartTotal",shoppingCart.finalPrice());
        return "cart";
    }
}
