package codes.harsha.scopes.repo;

import codes.harsha.scopes.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface ItemRepo extends CrudRepository<Item, Id> {
    Optional<Item> findById(long id);
}
