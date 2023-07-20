package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для {@link Certificate}
 */
@Repository
public interface CertificateDAO extends JpaRepository<Certificate, Long> {
}

