package codes.harsha.scopes.service;

import codes.harsha.scopes.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionScopeComponent {

    @Autowired
    private  ShopHomeService shopHomeService;

    private List<String> names = new LinkedList<>();

    private Map<Item, Integer> cartItems = new HashMap<>();

    public List<String> getNames() {
        return names;
    }

    private String errMessage = "";

    public void addName(String name) {
        names.add(name);
    }

    public String getInstanceId(){
        return this.toString();
    }

    public void addToCart(String itemId){
        errMessage = "";
        Item item = shopHomeService.getItem(Long.parseLong(itemId));

        if(cartItems.containsKey(item) && item.getStock() == cartItems.get(item)){
            errMessage = "You cannot add more " + item.getName()+"s.";
            return;
        }

        if(cartItems.containsKey(item)){
            cartItems.put(item, cartItems.get(item)+1);
        } else {
            cartItems.put(item,1);
        }
    }

    public Map<Item, Integer> getCartItems(){
        return cartItems;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void removeFromCart(String itemid) {
        errMessage = "";
        Item item = shopHomeService.getItem(Long.parseLong(itemid));

        if(cartItems.containsKey(item)){
            cartItems.remove(item);
        }
    }
}
