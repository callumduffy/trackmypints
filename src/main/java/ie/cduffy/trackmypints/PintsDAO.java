package ie.cduffy.trackmypints;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

public interface PintsDAO extends MongoRepository<PintData, String> {

//    public void insertPint(String name, PintData pintData);
//
//    public PintData getPintDataByName(String name);
}
