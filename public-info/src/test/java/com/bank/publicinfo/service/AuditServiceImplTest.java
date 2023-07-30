package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.repository.AuditDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    @Mock
    private AuditDAO auditDAOMock;

    @InjectMocks
    private  AuditServiceImpl service;

    Audit audit = Audit.builder().id(1l).createdAt(new Timestamp(0L)).modifiedAt(new Timestamp(0L)).modifiedBy("user")
            .createdBy("user").entityJson("json").newEntityJson("new_json").operationType("UPDATE").entityType("Atm")
            .build();

    @Test
    void allAudit() {
        List<Audit> data = List.of(audit);

        when(auditDAOMock.findAll()).thenReturn(data);

        List<Audit> result = service.allAudit();

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}