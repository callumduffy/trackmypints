package ie.cduffy.trackmypints;

import com.mongodb.WriteResult;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class PintsRepositoryImpl implements PintsRepoCustom {

    private MongoTemplate mongoTemplate;

    Logger logger = LoggerFactory.getLogger(PintsService.class);

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
        Query query = new Query(Criteria.where("name").is(pintData.getName()));
        Update update = new Update();
        update.set("priceTotal", pintData.getPriceTotal() + price);

        mongoTemplate.updateFirst(query, update, PintData.class);
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
        mongoTemplate.insert(pintData);
    }
}
