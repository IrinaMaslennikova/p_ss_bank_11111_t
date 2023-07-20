package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.repository.AuditDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service для {@link Audit}
 */

@Service
public class AuditServiceImpl implements AuditService {
    private final AuditDAO auditDAO;
    @Autowired
    public AuditServiceImpl(AuditDAO auditDAO) {this.auditDAO = auditDAO;}
    @Override
    public List<Audit> allAudit() {
        return auditDAO.findAll();
    }
}

