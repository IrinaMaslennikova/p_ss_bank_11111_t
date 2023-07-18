package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для {@link BankDetails}
 */
@Repository
public interface BankDetailsDAO extends JpaRepository<BankDetails, Long> {
}
