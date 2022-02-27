package codes.harsha.scopes.controller;

import codes.harsha.scopes.model.Item;
import codes.harsha.scopes.service.SessionScopeComponent;
import codes.harsha.scopes.service.ShopHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShoppingController {

    @Autowired
    private ShopHomeService shopHomeService;

    @Autowired
    private SessionScopeComponent sessionScopeComponent;

    @GetMapping("/secure/session")
    public String greeting(Model model) {
        model.addAttribute("items", shopHomeService.getItems());
        model.addAttribute("cartItems", sessionScopeComponent.getCartItems());
        model.addAttribute("id", sessionScopeComponent.getInstanceId());
        model.addAttribute("errMessage", sessionScopeComponent.getErrMessage());
        return "shopping-cart";
    }

    @GetMapping("/secure/session/{itemid}")
    public String addItemToCart (Model model, @PathVariable String itemid) {
        sessionScopeComponent.addToCart(itemid);
        model.addAttribute("items", shopHomeService.getItems());
        model.addAttribute("cartItems", sessionScopeComponent.getCartItems());
        model.addAttribute("id", sessionScopeComponent.getInstanceId());
        model.addAttribute("errMessage", sessionScopeComponent.getErrMessage());
        return "shopping-cart";
    }

    @GetMapping("/secure/session/remove/{itemid}")
    public String removeItemFromCart (Model model, @PathVariable String itemid) {
        sessionScopeComponent.removeFromCart(itemid);
        model.addAttribute("items", shopHomeService.getItems());
        model.addAttribute("cartItems", sessionScopeComponent.getCartItems());
        model.addAttribute("id", sessionScopeComponent.getInstanceId());
        model.addAttribute("errMessage", sessionScopeComponent.getErrMessage());
        return "shopping-cart";
    }
}
