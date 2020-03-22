package ie.cduffy.trackmypints.model;

import java.util.HashMap;

public class PintUser {

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
}
