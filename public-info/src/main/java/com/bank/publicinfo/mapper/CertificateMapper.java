package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.Certificate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper для {@link Certificate}
 */

@Mapper
public interface CertificateMapper {
    CertificateMapper mapper = Mappers.getMapper(CertificateMapper.class);

    @Mapping(target = "id", ignore = true)
    Certificate certificateDTOtoCertificate(CertificateDTO certificateDTO);

    CertificateDTO certificateToCertificateDTO(Certificate certificate);
}

