package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.PintData;
import ie.cduffy.trackmypints.model.PintUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;

public class PintsRepositoryImpl implements PintsRepoCustom {

    private MongoTemplate mongoTemplate;

    private Logger logger = LoggerFactory.getLogger(PintsRepositoryImpl.class);

    public PintsRepositoryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PintUser getPintUserByName(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        try{
            return mongoTemplate.findOne(query, PintUser.class);
        }catch(Exception e){
            logger.info("Error retrieving data for username: " + username);
            return null;
        }
    }

    @Override
    public void updatePint(PintUser pintUser) {
        logger.info("Updating pint.");
        mongoTemplate.save(pintUser, "pintdata");
    }

    @Override
    public HashMap<String, PintData> getAllPintData(String username) {
        return getPintUserByName(username).getPintData();
    }

    @Override
    public boolean isPintInDB(String username, String pintName) {
        return getPintUserByName(username).getPintData().containsKey(pintName);
    }

    @Override
    public void addPint(PintUser pintUser) {
        logger.info("Inserting pint.");
        mongoTemplate.save(pintUser, "pintdata");
    }
}
