package ie.cduffy.trackmypints.service;

import ie.cduffy.trackmypints.model.PintData;
import ie.cduffy.trackmypints.dao.PintsRepository;
import ie.cduffy.trackmypints.model.PintUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PintsService {

    private PintsRepository pintsRepository;

    private Logger logger = LoggerFactory.getLogger(PintsService.class);

    public PintsService(PintsRepository pintsRepository){
        this.pintsRepository = pintsRepository;
    }

    public void addPint(String pintName, Double price){
        String username = getUsername();
        PintUser pu = getPintUserByName(username);

        if(pu != null){
            PintData p = pu.getPintDataByName(pintName);
            if(p!=null){
                logger.info("Incrementing count for type: " + pintName);
                pu.addOrIncrementPint(pintName, price);
                logger.info("Pint incremented.");
                pintsRepository.updatePint(pu);
            }
            else{
                logger.info("New pint being added of type: " + pintName);
                pu.addOrIncrementPint(pintName, price);
                pintsRepository.addPint(pu);
            }
        }
        else{
            logger.info("Initiating new registered user with a pint of:" + pintName);
            pu = new PintUser(username);
            pu.addOrIncrementPint(pintName, price);
            pintsRepository.addPint(pu);
        }

    }

    public PintData getPintDataByName(String pintName){
        String username = getUsername();
        return getPintDataFromUser(getPintUserByName(username), pintName);
    }

    public HashMap<String, PintData> getAllPintData(){
        String username = getUsername();
        return pintsRepository.getAllPintData(username);
    }

    private PintData getPintDataFromUser(PintUser pintUser, String pintName){
        return pintUser.getPintDataByName(pintName);
    }

    private PintUser getPintUserByName(String username){
        return pintsRepository.getPintUserByName(username);
    }

    private String getUsername(){
        UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("USERNAME:" + ud.getUsername());
        return ud.getUsername();
    }
}
