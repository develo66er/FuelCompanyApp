package com.dao;

import com.connection.ConnectionFactory;
import com.models.Fuel;
import com.models.PetrolStation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuelDAOIml implements FuelDAO {

    @Override
    public List<Fuel> getFuelsByPetrolStation(long stationId) {
        List<Fuel> list = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("select * from fuel f inner join petrolstation_fuel pf on f.fuel_id=pf.fuel_id where pf.petrolstation_id =" + stationId)) {

            while (rs.next()) {

                Fuel fuel = new Fuel();

                fuel.setId(rs.getInt("fuel_id"));

                fuel.setName(rs.getString("fuel_name"));

                fuel.setType(rs.getString("fuel_type"));

                fuel.setPrice(rs.getDouble("fuel_price"));

                fuel.setPetrolstationId(stationId);

                list.add(fuel);
            }

            return list;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Fuel> getAllFuels() {
        List<Fuel> list = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("select f.fuel_id as fuel_id,f.fuel_name as fuel_name,f.fuel_type as fuel_type,pf.petrolstation_id as petrolstation_id,pf.fuel_price as fuel_price from fuel f inner join petrolstation_fuel pf on f.fuel_id = pf.fuel_id")) {

            while (rs.next()) {

                Fuel fuel = new Fuel();

                fuel.setId(rs.getInt("fuel_id"));

                fuel.setName(rs.getString("fuel_name"));

                fuel.setType(rs.getString("fuel_type"));

                fuel.setPrice(rs.getDouble("fuel_price"));

                fuel.setPetrolstationId(Long.parseLong(rs.getString("petrolstation_id")));

                list.add(fuel);
            }

            return list;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public Fuel getFuelByPetrolStation(long stationId, long fuelId) {
        Fuel fuel = new Fuel();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("select * from fuel f inner join petrolstation_fuel pf on f.fuel_id=pf.fuel_id where pf.petrolstation_id =" + stationId + " and pf.fuel_id=" + fuelId)) {

            if (rs.next()) {

                fuel.setId(rs.getInt("fuel_id"));

                fuel.setName(rs.getString("fuel_name"));

                fuel.setType(rs.getString("fuel_type"));

                fuel.setPrice(rs.getDouble("fuel_price"));

                fuel.setPetrolstationId(stationId);

            }

            return fuel;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public long insertFuelByPetrolStation(long stationId, Fuel fuel) {
        long fuelId = -1;
        try (Connection connection = ConnectionFactory.getConnection();) {

            try (PreparedStatement ps1 = connection.prepareStatement("INSERT INTO fuel (fuel_name,fuel_type) VALUES ( ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement ps2 = connection.prepareStatement("INSERT INTO petrolstation_fuel (fuel_id,petrolstation_id,fuel_price) VALUES ( ?, ?,?)", Statement.RETURN_GENERATED_KEYS);) {
                connection.setAutoCommit(false);
                ps1.setString(1, fuel.getName());

                ps1.setString(2, fuel.getType());

                ps1.executeUpdate();

                try (ResultSet generatedKeys = ps1.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        fuelId = generatedKeys.getLong(1);
                        ps2.setLong(1, fuelId);
                        ps2.setLong(2, stationId);
                        ps2.setDouble(3, fuel.getPrice());
                        ps2.executeUpdate();
                        connection.commit();
                    }

                } catch (Exception e) {
                    throw e;
                }
            } catch (Exception ex) {
                connection.rollback();
                throw ex;

            } finally {
                connection.setAutoCommit(true);

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return fuelId;
    }

    @Override
    public boolean updateFuelByPetrolStation(long stationId, Fuel fuel) {

        try (Connection connection = ConnectionFactory.getConnection();) {

            try (PreparedStatement ps1 = connection.prepareStatement("update fuel SET fuel_name= ? ,fuel_type=? where fuel_id in (select fuel_id from petrolstation_fuel where fuel_id=? and petrolstation_id=?)");
                    PreparedStatement ps2 = connection.prepareStatement("update petrolstation_fuel SET fuel_price= ? , petrolstation_id=? where fuel_id=? and petrolstation_id=?");) {
                connection.setAutoCommit(false);
                ps1.setString(1, fuel.getName());

                ps1.setString(2, fuel.getType());
                ps1.setLong(3, fuel.getId());
                ps1.setLong(4, stationId);

                ps1.executeUpdate();

                ps2.setDouble(1, fuel.getPrice());
                ps2.setLong(2, fuel.getPetrolstationId());

                ps2.setLong(3, fuel.getId());
                ps2.setLong(4, stationId);
                ps2.executeUpdate();
                connection.commit();

            } catch (SQLException ex) {
                connection.rollback();
                throw ex;

            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
            return false;

        }
        return true;
    }

    @Override
    public int deletePetrolStationFuels(long stationId) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps1 = connection.prepareStatement("delete from petrolstation_fuel where petrolstation_id=?");
                PreparedStatement ps2 = connection.prepareStatement("delete from fuel where fuel_id=?");) {

            //взять заправку и вернуть список id топлива
            List<Fuel> fuels = getFuelsByPetrolStation(stationId);

            ps1.setLong(1, stationId);

            int i = ps1.executeUpdate();
            if (i > 0) {
                for (Fuel fuel : fuels) {
                    ps2.setLong(1, fuel.getId());
                    ps2.executeUpdate();
                }
            } else {
                return -1;
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return -1;
    }

    @Override
    public int deletePetrolStationFuel(long stationId, long fuelId) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps1 = connection.prepareStatement("delete from fuel where fuel_id =?");
                PreparedStatement ps2 = connection.prepareStatement("delete from petrolstation_fuel where fuel_id =? and petrolstation_id=?");) {
            Fuel fuel = getFuelByPetrolStation(stationId, fuelId);
            if (fuel != null) {
                ps2.setLong(1, fuelId);
                ps2.setLong(2, stationId);
                int i = ps2.executeUpdate();
                if (i > 0) {
                    ps1.setLong(1, fuel.getId());
                    return ps1.executeUpdate();
                } else {
                    return -1;
                }

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return -1;
    }

}
