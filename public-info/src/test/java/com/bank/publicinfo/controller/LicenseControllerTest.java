package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.service.LicenseService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LicenseControllerTest {

    @Mock
    private final LicenseMapper mapper = Mappers.getMapper(LicenseMapper.class);
    @Mock
    LicenseService service;
    @InjectMocks
    LicenseController controller;
    License license = License.builder().id(1L).photo(new byte[1]).bankDetails(new BankDetails()).build();
    LicenseDTO licenseDTO = mapper.licenseToLicenseDTO(license);
    @Test
    void getAllLicenses() {
        List<License> data = List.of(license);
        when(service.allLicenses()).thenReturn(data);

        ResponseEntity<List<LicenseDTO>> response = controller.getAllLicenses();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getLicenseById() {
        when(service.getLicenseById(1)).thenReturn(license);

        ResponseEntity<LicenseDTO> response = controller.getLicenseById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void addNewLicense() {
        ResponseEntity<LicenseDTO> response = (ResponseEntity<LicenseDTO>) controller.addNewLicense(licenseDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateLicenseById() {
        ResponseEntity<LicenseDTO> response = (ResponseEntity<LicenseDTO>) controller.updateLicenseById(licenseDTO, 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteLicenseById() {
        ResponseEntity<LicenseDTO> response = (ResponseEntity<LicenseDTO>) controller.deleteLicenseById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}