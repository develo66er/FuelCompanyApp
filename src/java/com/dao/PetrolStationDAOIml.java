package com.dao;

import com.connection.ConnectionFactory;
import com.models.PetrolStation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natali
 */
public class PetrolStationDAOIml implements PetrolStationDAO {

    @Override
    public List<PetrolStation> getPetrolStationsByCompany(long companyId) {
        List<PetrolStation> list = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM petrolstation where company_id=" + companyId);) {

            while (rs.next()) {

                PetrolStation station = new PetrolStation();

                station.setId(rs.getInt("petrolstation_id"));

                station.setAddress(rs.getString("petrolstation_address"));

                station.setStartTime(rs.getString("start_time"));

                station.setFinishTime(rs.getString("finish_time"));

                list.add(station);
            }

            return list;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public int deleteCompanyPetrolStations(long companyId) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                //взять все заправки для компании и удалить у них все раздаточные колонки

                PreparedStatement ps = connection.prepareStatement("delete from petrolstation where company_id=?");) {

            List<PetrolStation> petrolStations = getPetrolStationsByCompany(companyId);

            DispenserDAO dispenserDAO = new DispenserDAOIml();
                        FuelDAO fuelDAO = new FuelDAOIml();


            for (PetrolStation petrolStation : petrolStations) {
                dispenserDAO.deletePetrolStationDispensers(petrolStation.getId());
                fuelDAO.deletePetrolStationFuels(petrolStation.getId());
            }

            ps.setLong(1, companyId);

            return ps.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return -1;
    }

    @Override
    public long insertPetrolStationsByCompany(long companyId, PetrolStation station) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO petrolstation (petrolstation_address,start_time,finish_time,company_id) VALUES ( ?, ?, ?,?)",Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, station.getAddress());

            ps.setTime(2, station.getStartTime());

            ps.setTime(3, station.getFinishTime());
            ps.setLong(4, companyId);

            int i = ps.executeUpdate();

            if (i == 1) {

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }
            } else {
                return -1;
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
            return -1;

        }

        return -1;
    }

    @Override
    public boolean updatePetrolStationsByCompany(long companyId, PetrolStation station) {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("UPDATE petrolstation SET petrolstation_address=?, start_time=?, finish_time=?  WHERE company_id=? and petrolstation_id=?");) {

            ps.setString(1, station.getAddress());

            ps.setTime(2, station.getStartTime());

            ps.setTime(3, station.getFinishTime());

            ps.setLong(4, companyId);

            ps.setLong(5, station.getId());

            int i = ps.executeUpdate();

            if (i == 1) {

                return true;

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return false;
    }

    @Override
    public int deleteCompanyPetrolStation(long companyId, long petrolStationId) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("delete from petrolstation where company_id=? and petrolstation_id=?");) {
            
            DispenserDAO dispenserDAO = new DispenserDAOIml();

            dispenserDAO.deletePetrolStationDispensers(petrolStationId);

            ps.setLong(1, companyId);
            ps.setLong(2, petrolStationId);

            return ps.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return -1;
    }

    @Override
    public PetrolStation getPetrolStationByCompany(long companyId, long petrolsatationId) {
        PetrolStation station = new PetrolStation();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM petrolstation where company_id=" + companyId + "and petrolstation_id=" + petrolsatationId);) {

            if (rs.next()) {

                station.setId(rs.getInt("petrolstation_id"));

                station.setAddress(rs.getString("petrolstation_address"));

                station.setStartTime(rs.getString("start_time"));

                station.setFinishTime(rs.getString("finish_time"));

            }

            return station;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public List<PetrolStation> getAllPetrolStations() {

        List<PetrolStation> list = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM petrolstation");) {

            while (rs.next()) {

                PetrolStation station = new PetrolStation();

                station.setId(rs.getInt("petrolstation_id"));

                station.setAddress(rs.getString("petrolstation_address"));

                station.setStartTime(rs.getString("start_time"));

                station.setFinishTime(rs.getString("finish_time"));

                list.add(station);
            }

            return list;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;

    }

}
