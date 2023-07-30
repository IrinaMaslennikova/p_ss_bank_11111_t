package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.repository.LicenseDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LicenseServiceImplTest {
    @Mock
    private LicenseDAO licenseDAOMock;

    @InjectMocks
    private LicenseServiceImpl service;

    License license1 = License.builder().id(1L).photo(new byte[1]).bankDetails(new BankDetails()).build();
    License license2 = License.builder().id(2L).photo(new byte[1]).bankDetails(new BankDetails()).build();

    @Test
    void allLicenses() {
        List<License> data = List.of(license1, license2);

        when(licenseDAOMock.findAll()).thenReturn(data);

        List<License> result = service.allLicenses();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void add() {
        License license = License.builder().id(3L).photo(new byte[1]).bankDetails(new BankDetails()).build();

        service.add(license);

        verify(licenseDAOMock).save(license);
    }

    @Test
    void delete() {
        service.delete(1L);

        verify(licenseDAOMock).deleteById(1L);
    }

    @Test
    void edit() {
        License newLicense = License.builder().id(2L).photo(new byte[1]).bankDetails(new BankDetails()).build();

        service.edit(newLicense, newLicense.getId());

        verify(licenseDAOMock).save(newLicense);
    }

    @Test
    void getLicenseById() {
        List<License> data = List.of(license1, license2);
        long id = 1L;

        when(licenseDAOMock.findById(id)).thenReturn(Optional.of(data.get((int) id - 1)));

        Optional<License> result = Optional.ofNullable(service.getLicenseById(id));

        assertNotNull(result.get());
        assertEquals(1L, result.get().getId());
    }
}