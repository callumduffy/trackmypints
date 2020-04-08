package ie.cduffy.trackmypints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "pintusers")
public class PintUser {

    @Id
    private String id;

    private String username;
    private HashMap<String, PintData> pints;

    public PintUser(String username){
        this.username = username;
        this.pints = new HashMap<>();
    }

    public PintData getPintDataByName(String name){
        if(pints.containsKey(name)){
            return pints.get(name);
        }
        else{
            return null;
        }
    }

    public void addOrIncrementPint(String pintName, Double price){
        PintData p = getPintDataByName(pintName);

        if(p==null){
            p = new PintData(price);
            this.pints.put(pintName, p);
        }
        else{
            p.increment(price);
            this.pints.put(pintName, p);
        }
    }

    public HashMap<String, PintData> getPintData(){
        return this.pints;
    }
}
