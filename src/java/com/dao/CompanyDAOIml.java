package com.dao;

import com.connection.ConnectionFactory;
import com.models.Company;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOIml implements CompanyDAO {

    private static PetrolStationDAOIml petrolStationDAO = new PetrolStationDAOIml();

    @Override
    public List<Company> findAll() {
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM company");) {

            List<Company> companies = new ArrayList<>();

            while (rs.next()) {

                Company company = new Company();

                company.setId(rs.getInt("company_id"));

                company.setName(rs.getString("company_name"));

                company.setAddress(rs.getString("company_address"));

                company.setInn(rs.getString("company_inn"));
                company.setPhone(rs.getString("company_phone"));
                company.setPetrolStations(petrolStationDAO.getPetrolStationsByCompany(company.getId()));

                companies.add(company);

            }

            return companies;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public Company findById(String id) {
        try (Connection connection = ConnectionFactory.getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM company WHERE company_id=" + id)) {

            if (rs.next()) {

                Company company = new Company();

                company.setId(rs.getInt("company_id"));

                company.setName(rs.getString("company_name"));

                company.setAddress(rs.getString("company_address"));

                company.setInn(rs.getString("company_inn"));

                company.setPhone(rs.getString("company_phone"));

                company.setPetrolStations(petrolStationDAO.getPetrolStationsByCompany(company.getId()));

                return company;

            }

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return null;

    }

    @Override
    public List<Company> findByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long insertCompany(Company company) {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("INSERT INTO company (company_name,company_address,company_inn,company_phone) VALUES ( ?, ?, ?,?)",Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, company.getName());

            ps.setString(2, company.getAddress());

            ps.setString(3, company.getInn());
            ps.setString(4, company.getPhone());

            int affectedRows = ps.executeUpdate();
        if (affectedRows != 0)
        {
            // get the latest inserted id :
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next())
            {
                return generatedKeys.getLong(1);
            }
        }

        } catch (SQLException ex) {

            ex.printStackTrace();
            return -1;

        }

        return -1;
    }

    @Override
    public boolean updateCompany(Company company) {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("UPDATE company SET company_name=?, company_address=?, company_inn=? , company_phone=? WHERE company_id=?");) {
            ps.setString(1, company.getName());

            ps.setString(2, company.getAddress());

            ps.setString(3, company.getInn());

            ps.setString(4, company.getPhone());

            ps.setLong(5, company.getId());

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
    public boolean deleteCompany(long id) {
        try (Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement("delete from company where company_id=?");) {

            ps.setLong(1, id);
            petrolStationDAO.deleteCompanyPetrolStations(id);

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {

            ex.printStackTrace();

        }

        return false;
    }

}
