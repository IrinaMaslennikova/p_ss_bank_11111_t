package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для {@link License}
 */
@Repository
public interface LicenseDAO extends JpaRepository<License, Long> {
}

