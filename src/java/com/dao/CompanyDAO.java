package com.dao;

import com.models.Company;
import java.util.List;

public interface CompanyDAO {
    public List<Company> findAll();
    public Company findById(String id);
    public List<Company> findByName();
    public long insertCompany(Company company);
    public boolean updateCompany(Company company);
    public boolean deleteCompany(long id);
}
