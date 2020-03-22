package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.PintData;
import ie.cduffy.trackmypints.service.PintsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class PintsRepositoryImpl implements PintsRepoCustom {

    private MongoTemplate mongoTemplate;

    private Logger logger = LoggerFactory.getLogger(PintsService.class);

    public PintsRepositoryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PintData getPintDataByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));

        try{
            return mongoTemplate.findOne(query, PintData.class);
        }catch(Exception e){
            logger.info("Error retrieving data for name: " + name);
            return null;
        }
    }

    @Override
    public void updatePintDataByName(PintData pintData, Double price) {
        pintData.increment(price);
        logger.info("Pint incremented.");
        mongoTemplate.save(pintData, "pintdata");
    }

    @Override
    public List<PintData> getAllPintData() {
        return mongoTemplate.findAll(PintData.class);
    }

    @Override
    public boolean isPintInDB(String name) {
        return getPintDataByName(name) != null;
    }

    @Override
    public void addPint(PintData pintData) {
        logger.info("Inserting pint.");
        mongoTemplate.save(pintData, "pintdata");
    }
}
