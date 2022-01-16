package com.dao;

import com.models.Fuel;
import java.util.List;

public interface FuelDAO {

    public List<Fuel> getFuelsByPetrolStation(long stationId);
    
    public List<Fuel> getAllFuels();

    public Fuel getFuelByPetrolStation(long stationId, long fuelId);

    public long insertFuelByPetrolStation(long stationId, Fuel fuel);

    public boolean updateFuelByPetrolStation(long stationId, Fuel fuel);

    public int deletePetrolStationFuels(long stationId);

    public int deletePetrolStationFuel(long stationId, long fuelId);

}
