package com.dao;

import com.models.PetrolStation;
import java.util.List;

public interface PetrolStationDAO {

    public List<PetrolStation> getPetrolStationsByCompany(long companyId);
    public List<PetrolStation> getAllPetrolStations();

    public PetrolStation getPetrolStationByCompany(long companyId, long petrolsatationId);

    public long insertPetrolStationsByCompany(long companyId, PetrolStation station);

    public boolean updatePetrolStationsByCompany(long companyId, PetrolStation station);

    public int deleteCompanyPetrolStations(long companyId);

    public int deleteCompanyPetrolStation(long companyId, long petrolStationId);

}
