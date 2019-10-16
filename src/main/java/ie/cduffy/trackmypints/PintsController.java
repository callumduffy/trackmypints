package ie.cduffy.trackmypints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PintsController {

    PintsService pintsService;

    public PintsController(PintsService pintsService){
        this.pintsService = pintsService;
    }

    @RequestMapping(value = "/pint", method = RequestMethod.POST)
    public void addPint(@RequestParam String name, @RequestParam Double price){
        pintsService.addPint(name,price);
    }
}
