package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.PintData;
import ie.cduffy.trackmypints.model.PintUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PintsRepository extends MongoRepository<PintUser, String>, PintsRepoCustom {
    //provides basic CRUD functionality with just this
}
