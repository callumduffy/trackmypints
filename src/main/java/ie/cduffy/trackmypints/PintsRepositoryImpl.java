package ie.cduffy.trackmypints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class PintsRepositoryImpl implements PintsRepoCustom {

    private MongoTemplate mongoTemplate;

    public PintsRepositoryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public PintData getPintDataByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, PintData.class);
    }

    @Override
    public void updatePintDataByName(PintData pintData) {

    }

    @Override
    public List<PintData> getAllPintData() {
        return mongoTemplate.findAll(PintData.class);
    }

    @Override
    public boolean isPintInDB(String name) {
        return (getPintDataByName(name)!=null) ? true : false;
    }

    @Override
    public List<PintData> getPintDataByMonth() {
        return null;
    }
}
