package com.dao;

import com.connection.ConnectionFactory;
import com.models.Dispenser;
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
public class DispenserDAOIml implements DispenserDAO {

    @Override
    public List<Dispenser> getDispensersByPetrolStation(long stationId) {
        List<Dispenser> list = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM dispenser where petrolstation_id=" + stationId);) {

            while (rs.next()) {

                Dispenser dispenser = new Dispenser();

                dispenser.setId(rs.getInt("dispenser_id"));

                dispenser.setType(rs.getString("dispenser_type"));

                dispenser.setCount(rs.getInt("dispenser_count"));

                dispenser.setModel(rs.getString("dispenser_model"));

                dispenser.setPetrolstationId(stationId);

                list.add(dispenser);
            }

            return list;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public int deletePetrolStationDispensers(long stationId) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("delete from dispenser where petrolstation_id=?");) {

            ps.setLong(1, stationId);

            return ps.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return -1;
    }

    @Override
    public long insertDispenserByPetrolStation(long stationId, Dispenser dispenser) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO dispenser (dispenser_type, dispenser_count, dispenser_model, petrolstation_id) VALUES ( ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, dispenser.getType());

            ps.setInt(2, dispenser.getCount());

            ps.setString(3, dispenser.getModel());
            ps.setLong(4, stationId);

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
    public boolean updateDispenserByPetrolStation(long stationId, Dispenser dispenser) {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("UPDATE dispenser SET dispenser_type=?, dispenser_count=?, dispenser_model=?, petrolstation_id=?  WHERE dispenser_id=? and petrolstation_id=?");) {

            ps.setString(1, dispenser.getType());

            ps.setInt(2, dispenser.getCount());

            ps.setString(3, dispenser.getModel());

            ps.setLong(4, dispenser.getPetrolstationId());

            ps.setLong(5, dispenser.getId());

            ps.setLong(6, stationId);

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
    public int deletePetrolStationDispenser(long stationId, long dispenserId) {
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("delete from dispenser where dispenser_id=? and petrolstation_id=?");) {

            ps.setLong(1, dispenserId);
            ps.setLong(2, stationId);

            return ps.executeUpdate();

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return -1;
    }

    @Override
    public Dispenser getDispenserByPetrolStation(long stationId, long dispenserId) {
        Dispenser dispenser = new Dispenser();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM dispenser where dispenser_id=" + dispenserId + "and petrolstation_id=" + stationId);) {

            if (rs.next()) {

                dispenser.setId(rs.getInt("dispenser_id"));

                dispenser.setType(rs.getString("dispenser_type"));

                dispenser.setCount(rs.getInt("dispenser_count"));

                dispenser.setModel(rs.getString("dispenser_model"));

                dispenser.setPetrolstationId(rs.getLong("petrolstation_id"));

            }

            return dispenser;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Dispenser> getAllDispensers() {
        List<Dispenser> list = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM dispenser")) {

            while (rs.next()) {

                Dispenser dispenser = new Dispenser();

                dispenser.setId(rs.getInt("dispenser_id"));

                dispenser.setType(rs.getString("dispenser_type"));

                dispenser.setCount(rs.getInt("dispenser_count"));

                dispenser.setModel(rs.getString("dispenser_model"));

                dispenser.setPetrolstationId(rs.getLong("petrolstation_id"));

                list.add(dispenser);
            }

            return list;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;

    }

}
