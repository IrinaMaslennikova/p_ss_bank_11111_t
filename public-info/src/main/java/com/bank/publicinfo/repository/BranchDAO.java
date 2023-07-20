package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository для {@link Branch}
 */
@Repository
public interface BranchDAO extends JpaRepository<Branch, Long> {
}

