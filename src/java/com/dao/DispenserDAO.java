package com.dao;

import com.models.Dispenser;
import java.util.List;

public interface DispenserDAO {
    public List<Dispenser> getAllDispensers();

    public List<Dispenser> getDispensersByPetrolStation(long stationId);
    public Dispenser getDispenserByPetrolStation(long stationId, long dispenserId);

    public long insertDispenserByPetrolStation(long stationId, Dispenser station);

    public boolean updateDispenserByPetrolStation(long stationId, Dispenser station);

    public int deletePetrolStationDispensers(long stationId);

    public int deletePetrolStationDispenser(long stationId, long dispenserId);

}
