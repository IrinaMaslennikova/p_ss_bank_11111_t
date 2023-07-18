package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.License;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper для {@link License}
 */

@Mapper
public interface LicenseMapper {
    LicenseMapper mapper = Mappers.getMapper(LicenseMapper.class);

    @Mapping(target = "id", ignore = true)
    License licenseDTOtoLicense(LicenseDTO licenseDTO);

    LicenseDTO licenseToLicenseDTO(License license);
}
