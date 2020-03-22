package ie.cduffy.trackmypints.service;

import ie.cduffy.trackmypints.model.PintData;
import ie.cduffy.trackmypints.dao.PintsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PintsService {

    private PintsRepository pintsRepository;

    private Logger logger = LoggerFactory.getLogger(PintsService.class);

    public PintsService(PintsRepository pintsRepository){
        this.pintsRepository = pintsRepository;
    }

    public void addPint(String name, Double price){
        PintData p = getPintDataByName(name);

        if(p!=null){
            logger.info("Incrementing count for type: " + name);
            pintsRepository.updatePintDataByName(p, price);
        }
        else{
            logger.info("New pint being added of type: " + name);
            p = new PintData(name,price);
            pintsRepository.addPint(p);
        }
    }

    public PintData getPintDataByName(String name){
        return pintsRepository.getPintDataByName(name);
    }

    public List<PintData> getAllPintData(){
        return pintsRepository.getAllPintData();
    }
}
