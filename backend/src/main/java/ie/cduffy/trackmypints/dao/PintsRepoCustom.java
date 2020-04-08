package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.PintData;
import ie.cduffy.trackmypints.model.PintUser;

import java.util.HashMap;

public interface PintsRepoCustom {
    public PintUser getPintUserByName(String username);

    public void updatePint(PintUser pintUser);

    public HashMap<String, PintData> getAllPintData(String username);

    public boolean isPintInDB(String username, String pintName);

    public void addPint(PintUser pintUser);
}
