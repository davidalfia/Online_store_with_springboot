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
public class CartController {

    @Autowired
    private ShoppingCart shoppingCart;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model, Product product){
        model.addAttribute("userProducts",shoppingCart.getShoppingCart());
        model.addAttribute("shoppingCartSize",shoppingCart.size());
        return "cart";
    }

    @PostMapping("/cart/delete")
    public String cart(@RequestParam("id") long id){
        shoppingCart.delete(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/emptyCart")
    public String emptyCart(){
        shoppingCart.deleteAll();
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model){
        try{
            productService.checkout(shoppingCart);
        }catch (Exception e){
            shoppingCart.deleteAll();
            model.addAttribute("products",productService.findTop5BestDiscount());
            model.addAttribute("shoppingCartSize",shoppingCart.size());
            model.addAttribute("errorMsg",e.getMessage());

        }finally {
            shoppingCart.deleteAll();
            model.addAttribute("products",productService.findTop5BestDiscount());
            model.addAttribute("shoppingCartSize",shoppingCart.size());
        }
        return "store";
    }
}
