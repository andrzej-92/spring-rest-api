package agency.repositories;

import agency.entity.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PoliciesRepository extends MongoRepository<Policy, String> {

}
