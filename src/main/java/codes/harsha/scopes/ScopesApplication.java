package codes.harsha.scopes;

import codes.harsha.scopes.model.Item;
import codes.harsha.scopes.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.UUID;

@SpringBootApplication
public class ScopesApplication {

	@Autowired
	private ItemRepo itemRepo;

	public static void main(String[] args) {
		SpringApplication.run(ScopesApplication.class, args);
	}

	@PostConstruct
	public void initData(){
		Item item = new Item();

		for(int i=0; i < 20; i++){
			item = new Item();
			item.setName("cart item "+i);
			item.setStock((i+1) * 2);
			int price = 50 + (int)(Math.random() * ((200 - 50) + 1));
			item.setPrice(price);

			itemRepo.save(item);
		}


	}

}
