package codes.harsha.scopes.service;

import codes.harsha.scopes.model.Item;
import codes.harsha.scopes.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShopHomeService {

    @Autowired
    private ItemRepo itemRepo;

    private List<Item> items = new LinkedList<>();

    @PostConstruct
    public void retrieveItems() {
        itemRepo.findAll().forEach(item -> {
            items.add(item);
        });
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem(long id){
        return itemRepo.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
