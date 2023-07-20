package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.exception.DataNotFoundException;
import com.bank.publicinfo.repository.CertificateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service для {@link Certificate}
 */

@Service
public class CertificateServiceImpl implements CertificateService {
    private final CertificateDAO certificateDAO;
    @Autowired
    public CertificateServiceImpl(CertificateDAO certificateDAO) {this.certificateDAO = certificateDAO;}

    @Override
    public List<Certificate> allCertificates() {
        return certificateDAO.findAll();
    }

    @Override
    public void add(Certificate certificate) {
        certificateDAO.save(certificate);
    }

    @Override
    public void delete(long id) {
        certificateDAO.deleteById(id);
    }

    @Override
    public void edit(Certificate certificate, long id) {
        certificate.setId(id);
        certificateDAO.save(certificate);
    }

    @Override
    public Certificate getCertificateById(long id) {
        Optional<Certificate> newCertificate = certificateDAO.findById(id);
        return newCertificate.orElseThrow(() -> new DataNotFoundException("Сертификат по id " + id + " не найден!"));
    }
}

