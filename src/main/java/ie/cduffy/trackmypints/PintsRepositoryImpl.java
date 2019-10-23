package ie.cduffy.trackmypints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class PintsRepositoryImpl implements PintsRepoCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public PintData getPintDataByName() {
        return null;
    }

    @Override
    public void updatePintDataByName() {

    }

    @Override
    public List<PintData> getAllPintData() {
        return null;
    }

    @Override
    public boolean isPintInDB() {
        return false;
    }

    @Override
    public List<PintData> getPintDataByMonth() {
        return null;
    }
}
