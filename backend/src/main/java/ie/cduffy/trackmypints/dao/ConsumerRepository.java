package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<Consumer, String>, ConsumerRepoCustom {
}
