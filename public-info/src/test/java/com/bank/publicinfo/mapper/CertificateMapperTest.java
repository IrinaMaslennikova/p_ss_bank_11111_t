package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class CertificateMapperTest {
    CertificateMapper mapper = Mappers.getMapper(CertificateMapper.class);
    BankDetails bankDetails = new BankDetails();

    @Test
    void certificateDTOtoCertificate() {
        CertificateDTO certificateDTO = new CertificateDTO(new byte[]{0}, bankDetails);
        Certificate certificate = mapper.certificateDTOtoCertificate(certificateDTO);
        CertificateDTO certificateDTONull = null;
        Certificate certificateNull = mapper.certificateDTOtoCertificate(certificateDTONull);
        assertEquals("Unexpected value found!", certificateNull, null);
        for(int i = 0; i< certificateDTO.getPhoto().length; i++) {
            assertEquals("Unexpected value found!", certificateDTO.getPhoto()[i], certificate.getPhoto()[i]);
        }
        assertEquals("Unexpected value found!", certificateDTO.getBankDetails(), certificate.getBankDetails());
    }

    @Test
    void certificateToCertificateDTO() {
        Certificate certificate = new Certificate(1, new byte[]{0, 0, 0}, bankDetails);
        CertificateDTO certificateDTO = mapper.certificateToCertificateDTO(certificate);
        Certificate certificateNull = null;
        CertificateDTO certificateDTONull = mapper.certificateToCertificateDTO(certificateNull);
        assertEquals("Unexpected value found!", certificateDTONull, null);
        for(int i = 0; i< certificateDTO.getPhoto().length; i++) {
            assertEquals("Unexpected value found!", certificateDTO.getPhoto()[i], certificate.getPhoto()[i]);
        }
        assertEquals("Unexpected value found!", certificateDTO.getBankDetails(), certificate.getBankDetails());
    }
}