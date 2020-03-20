package ie.cduffy.trackmypints.dao;

import ie.cduffy.trackmypints.model.PintData;

import java.util.List;

public interface PintsRepoCustom {
    public PintData getPintDataByName(String name);

    public void updatePintDataByName(PintData pintData, Double price);

    public List<PintData> getAllPintData();

    public boolean isPintInDB(String name);

    public void addPint(PintData pintData);
}
