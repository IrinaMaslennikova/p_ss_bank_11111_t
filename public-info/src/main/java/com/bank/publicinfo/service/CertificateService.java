package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Certificate;

import java.util.List;

public interface CertificateService {
    List<Certificate> allCertificates();

    void add(Certificate certificate);

    void delete(long id);

    void edit(Certificate certificate, long id);

    Certificate getCertificateById(long id);
}
