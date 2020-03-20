package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.PintData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PintsRepository extends MongoRepository<PintData, String>, PintsRepoCustom {
    //provides basic CRUD functionality with just this
}
