package ie.cduffy.trackmypints.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumerRepository extends MongoRepository<String, String>, ConsumerRepoCustom {
}
