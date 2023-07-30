package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.repository.CertificateDAO;
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
class CertificateServiceImplTest {

    @Mock
    private CertificateDAO certificateDAOMock;

    @InjectMocks
    private CertificateServiceImpl service;

    Certificate certificate1 = Certificate.builder().id(1L).photo(new byte[1]).bankDetails(new BankDetails()).build();
    Certificate certificate2 = Certificate.builder().id(2L).photo(new byte[1]).bankDetails(new BankDetails()).build();
    @Test
    void allCertificates() {
        List<Certificate> data = List.of(certificate1, certificate2);

        when(certificateDAOMock.findAll()).thenReturn(data);

        List<Certificate> result = service.allCertificates();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void add() {
        Certificate certificate = Certificate.builder().id(3L).photo(new byte[1]).bankDetails(new BankDetails()).build();

        service.add(certificate);

        verify(certificateDAOMock).save(certificate);
    }

    @Test
    void delete() {
        service.delete(1L);

        verify(certificateDAOMock).deleteById(1L);
    }

    @Test
    void edit() {
        Certificate newCertificate = Certificate.builder().id(2L).photo(new byte[1]).bankDetails(new BankDetails()).build();

        service.edit(newCertificate, newCertificate.getId());

        verify(certificateDAOMock).save(newCertificate);
    }

    @Test
    void getCertificateById() {
        List<Certificate> data = List.of(certificate1, certificate2);
        long id = 1L;

        when(certificateDAOMock.findById(id)).thenReturn(Optional.of(data.get((int) id - 1)));

        Optional<Certificate> result = Optional.ofNullable(service.getCertificateById(id));

        assertNotNull(result.get());
        assertEquals(1L, result.get().getId());
    }
}