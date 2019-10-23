package ie.cduffy.trackmypints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PintsService {

    @Autowired
    PintsRepository pintsRepository;

    Logger logger = LoggerFactory.getLogger(PintsService.class);

    public PintsService(){

    }

    public void addPint(String name, Double price){
        PintData p = new PintData(name, price);
        if(!pintsRepository.existsById(name)){
            pintsRepository.save(p);

        }

        throw new IllegalArgumentException("Pint name already in DB");
    }

    public PintData getPintDataByName(String name){
        return null;
    }

    public List<PintData> getAllPintData(){
        return null;
    }
}
