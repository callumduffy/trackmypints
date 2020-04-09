package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class ConsumerRepoImpl implements ConsumerRepoCustom {

    private MongoTemplate mongoTemplate;
    private Logger logger = LoggerFactory.getLogger(ConsumerRepoImpl.class);

    @Override
    public Consumer getConsumerByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));

        try{
            return mongoTemplate.findOne(query, Consumer.class, "credentials");
        }catch(Exception e){
            logger.info("Error retrieving data for username: " + username);
            return null;
        }
    }

    @Override
    public boolean isConsumerVerified(String username) {
        Consumer consumer = getConsumerByUsername(username);
        return consumer.isVerified();
    }

    @Override
    public boolean registerConsumer(Consumer consumer) {
        if(isConsumerRegistered(consumer.getUsername())){
            return false;
        }
        mongoTemplate.save(consumer, "credentials");
        return true;
    }

    @Override
    public boolean isConsumerRegistered(String username) {
        Consumer consumer = getConsumerByUsername(username);
        if(consumer != null){
            return true;
        }
        return false;
    }
}
