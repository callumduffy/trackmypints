package ie.cduffy.trackmypints;

import org.springframework.stereotype.Service;

@Service
public class PintsService {

    //TODO more functionality thoughts
    PintsDAO pintsDAO;

    public PintsService(PintsDAO pintsDAO){
        this.pintsDAO = pintsDAO;
    }

    public void addPint(String name, Double price){
        PintData p = new PintData(price);
        pintsDAO.insertPint(name, p);
    }
}
