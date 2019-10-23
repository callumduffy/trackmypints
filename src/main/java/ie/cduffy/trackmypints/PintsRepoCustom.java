package ie.cduffy.trackmypints;

import java.util.List;

public interface PintsRepoCustom {
    public PintData getPintDataByName();

    public void updatePintDataByName();

    public List<PintData> getAllPintData();

    public boolean isPintInDB();

    public List<PintData> getPintDataByMonth();
}
