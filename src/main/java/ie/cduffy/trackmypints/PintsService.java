package ie.cduffy.trackmypints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PintsService {

    private PintsRepository pintsRepository;

    Logger logger = LoggerFactory.getLogger(PintsService.class);

    public PintsService(PintsRepository pintsRepository){
        this.pintsRepository = pintsRepository;
    }

    public void addPint(String name, Double price){
        PintData p = getPintDataByName(name);

        if(p!=null){
            p.increment(price);
        }
        else{
            p = new PintData(name,price);
        }
        pintsRepository.save(p);
    }

    public PintData getPintDataByName(String name){
        return pintsRepository.getPintDataByName(name);
    }

    public List<PintData> getAllPintData(){
        return null;
    }
}
