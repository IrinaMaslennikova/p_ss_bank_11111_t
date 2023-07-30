package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.service.AtmService;
import com.bank.publicinfo.service.CertificateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CertificateControllerTest {

    @Mock
    private final CertificateMapper mapper = Mappers.getMapper(CertificateMapper.class);
    @Mock
    CertificateService service;
    @InjectMocks
    CertificateController controller;
    Certificate certificate = Certificate.builder().id(1L).photo(new byte[1]).bankDetails(new BankDetails()).build();
    CertificateDTO certificateDTO = mapper.certificateToCertificateDTO(certificate);
    @Test
    void getAllCertificates() {
        List<Certificate> data = List.of(certificate);
        when(service.allCertificates()).thenReturn(data);

        ResponseEntity<List<CertificateDTO>> response = controller.getAllCertificates();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getCertificateById() {
        when(service.getCertificateById(1)).thenReturn(certificate);

        ResponseEntity<CertificateDTO> response = controller.getCertificateById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void addNewCertificate() {
        ResponseEntity<CertificateDTO> response = (ResponseEntity<CertificateDTO>) controller.addNewCertificate(certificateDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateCertificateById() {
        ResponseEntity<CertificateDTO> response = (ResponseEntity<CertificateDTO>) controller.updateCertificateById(certificateDTO, 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteCertificateById() {
        ResponseEntity<CertificateDTO> response = (ResponseEntity<CertificateDTO>) controller.deleteCertificateById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}