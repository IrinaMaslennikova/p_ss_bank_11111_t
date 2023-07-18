package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper для {@link Audit}
 */

@Mapper
public interface AuditMapper {
    AuditMapper mapper = Mappers.getMapper(AuditMapper.class);

    AuditDTO auditToAuditDTO(Audit audit);
}
