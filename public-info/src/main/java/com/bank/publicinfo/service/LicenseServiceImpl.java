package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.exception.DataNotFoundException;
import com.bank.publicinfo.repository.LicenseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service для {@link License}
 */

@Service
public class LicenseServiceImpl implements LicenseService {
    private final LicenseDAO licenseDAO;
    @Autowired
    public LicenseServiceImpl(LicenseDAO licenseDAO) {this.licenseDAO = licenseDAO;}
    @Override
    public List<License> allLicenses() {
        return licenseDAO.findAll();
    }

    @Override
    public void add(License license) {
        licenseDAO.save(license);
    }

    @Override
    public void delete(long id) {
        licenseDAO.deleteById(id);
    }

    @Override
    public void edit(License license, long id) {
        license.setId(id);
        licenseDAO.save(license);
    }

    @Override
    public License getLicenseById(long id) {
        Optional<License> newLicense = licenseDAO.findById(id);
        return newLicense.orElseThrow(() -> new DataNotFoundException("Лицензия по id " + id + " не найден!"));
    }
}

