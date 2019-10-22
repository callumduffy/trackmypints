package ie.cduffy.trackmypints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PintsController {

    PintsService pintsService;

    Logger logger = LoggerFactory.getLogger(PintsController.class);

    public PintsController(PintsService pintsService){
        this.pintsService = pintsService;
    }

    @RequestMapping(value = "/pint", method = RequestMethod.POST)
    public void addPint(@RequestParam String name, @RequestParam Double price){
        logger.info("Adding PintData for: " + name);
        pintsService.addPint(name,price);
    }

    @RequestMapping(value ="/pint", method = RequestMethod.GET)
    public List<PintData> getAllPintData(){
        logger.info("Getting all PintData.");
        return pintsService.getAllPintData();
    }

    @RequestMapping(value = "/pint/{name}", method = RequestMethod.GET)
    public PintData getPint(@PathVariable String name){
        logger.info("Getting PintData of type: " + name);
        return pintsService.getPintDataByName(name);
    }
}
