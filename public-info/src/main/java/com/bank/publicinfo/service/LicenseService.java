package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.License;

import java.util.List;

public interface LicenseService {
    List<License> allLicenses();

    void add(License license);

    void delete(long id);

    void edit(License license, long id);

    License getLicenseById(long id);
}
