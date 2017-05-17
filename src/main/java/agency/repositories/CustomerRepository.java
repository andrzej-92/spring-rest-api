package agency.repositories;

import java.util.List;
import agency.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByName(String firstName);

    public List<Customer> findBySurname(String lastName);

}
