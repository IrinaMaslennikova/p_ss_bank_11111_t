package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository для {@link Audit}
 */
@Repository
public interface AuditDAO extends JpaRepository<Audit, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM public_bank_information.audit a where a.new_entity_json like :json order by id desc limit 1")
    Audit findAuditByNewEntityJson (String json);
}

