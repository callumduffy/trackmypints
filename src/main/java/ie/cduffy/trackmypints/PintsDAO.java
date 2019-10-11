package ie.cduffy.trackmypints;

import org.springframework.stereotype.Component;

@Component
public class PintsDAO {

    //TODO look into what data store we will be using?
    public void insertPint(String name, PintData pintData){
        try{
            //insert into chosen type of storage
        }catch(Exception e){
            //TODO need own exception types?
            //print exception
        }
    }
}
