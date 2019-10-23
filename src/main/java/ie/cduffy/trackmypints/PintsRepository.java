package ie.cduffy.trackmypints;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PintsRepository extends MongoRepository<PintData, String> {
    //provides basic CRUD fucntionality with just this
}
