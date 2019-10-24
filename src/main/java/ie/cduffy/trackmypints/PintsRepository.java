package ie.cduffy.trackmypints;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PintsRepository extends MongoRepository<PintData, String>, PintsRepoCustom {
    //provides basic CRUD functionality with just this
}
