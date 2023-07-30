package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class AuditMapperTest {
    AuditMapper mapper = Mappers.getMapper(AuditMapper.class);

    @Test
    void auditToAuditDTO() {
        Audit audit = new Audit(1, "entity_type", "operation_type", "created_by", "modified_by",
                new Timestamp(0L), new Timestamp(0L), null, "json");
        AuditDTO auditDTO = mapper.auditToAuditDTO(audit);
        Audit auditNull = null;
        AuditDTO auditDTONull = mapper.auditToAuditDTO(auditNull);
        assertEquals("Unexpected value found!", auditDTONull, null);
        assertEquals("Unexpected value found!", auditDTO.getEntityType(), audit.getEntityType());
        assertEquals("Unexpected value found!", auditDTO.getOperationType(), audit.getOperationType());
        assertEquals("Unexpected value found!", auditDTO.getCreatedBy(), audit.getCreatedBy());
        assertEquals("Unexpected value found!", auditDTO.getModifiedBy(), audit.getModifiedBy());
        assertEquals("Unexpected value found!", auditDTO.getCreatedAt(), audit.getCreatedAt());
        assertEquals("Unexpected value found!", auditDTO.getModifiedAt(), audit.getModifiedAt());
        assertEquals("Unexpected value found!", auditDTO.getEntityJson(), audit.getEntityJson());
        assertEquals("Unexpected value found!", auditDTO.getNewEntityJson(), audit.getNewEntityJson());
    }
}