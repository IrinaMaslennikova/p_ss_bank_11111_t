package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.mapper.AuditMapper;
import com.bank.publicinfo.service.AtmService;
import com.bank.publicinfo.service.AuditService;
import com.bank.publicinfo.service.AuditServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuditControllerTest {
    @Mock
    private final AuditMapper mapper = Mappers.getMapper(AuditMapper.class);
    @Mock
    AuditService service;
    @InjectMocks
    AuditController controller;

    Audit audit = Audit.builder().id(1l).createdAt(new Timestamp(0L)).modifiedAt(new Timestamp(0L)).modifiedBy("user")
            .createdBy("user").entityJson("json").newEntityJson("new_json").operationType("UPDATE").entityType("Atm")
            .build();

    @Test
    void getAllAudit() {
        List<Audit> data = List.of(audit);
        when(service.allAudit()).thenReturn(data);

        ResponseEntity<List<AuditDTO>> response = controller.getAllAudit();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}