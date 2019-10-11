package ie.cduffy.trackmypints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PintsController {

    //TODO need to sort DI -> autowire or not? look at FM code
    PintsService pintsService;

    @RequestMapping(value = "/pint", method = RequestMethod.POST)
    public void addPint(@RequestParam String name, @RequestParam Double price){
        pintsService.addPint(name,price);
    }
}
