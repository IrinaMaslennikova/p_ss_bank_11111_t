package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class LicenseMapperTest {
    LicenseMapper mapper = Mappers.getMapper(LicenseMapper.class);
    BankDetails bankDetails = new BankDetails();
    @Test
    void licenseDTOtoLicense() {
        LicenseDTO licenseDTO = new LicenseDTO(new byte[]{1}, bankDetails);
        License license = mapper.licenseDTOtoLicense(licenseDTO);
        LicenseDTO licenseDTONull = null;
        License licenseNull = mapper.licenseDTOtoLicense(licenseDTONull);
        assertEquals("Unexpected value found!", licenseNull, null);
        for(int i = 0; i< licenseDTO.getPhoto().length; i++) {
            assertEquals("Unexpected value found!", licenseDTO.getPhoto()[i], license.getPhoto()[i]);
        }
        assertEquals("Unexpected value found!", licenseDTO.getBankDetails(), license.getBankDetails());
    }

    @Test
    void licenseToLicenseDTO() {
        License license = new License(1, new byte[]{1}, bankDetails);
        LicenseDTO licenseDTO = mapper.licenseToLicenseDTO(license);
        License licenseNull = null;
        LicenseDTO licenseDTONull =mapper.licenseToLicenseDTO(licenseNull);
        assertEquals("Unexpected value found!", licenseDTONull, null);
        for(int i = 0; i< licenseDTO.getPhoto().length; i++) {
            assertEquals("Unexpected value found!", licenseDTO.getPhoto()[i], license.getPhoto()[i]);
        }
        assertEquals("Unexpected value found!", licenseDTO.getBankDetails(), license.getBankDetails());
    }
}