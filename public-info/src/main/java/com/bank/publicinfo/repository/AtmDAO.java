package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Atm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для {@link Atm}
 */

@Repository
public interface AtmDAO extends JpaRepository<Atm, Long> {
}
