package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.service.CertificateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-контроллер для таблицы certificate сущности {@link Certificate}
 */

@RestController
@AllArgsConstructor
@RequestMapping("/certificate")
public class CertificateController {
    private final CertificateService certificateService;

    @GetMapping
    public ResponseEntity<List<CertificateDTO>> getAllCertificates() {
        return new ResponseEntity<>(certificateService.allCertificates().stream().map(certificate -> CertificateMapper.mapper.certificateToCertificateDTO(certificate))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CertificateDTO> getCertificateById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(CertificateMapper.mapper.certificateToCertificateDTO(certificateService.getCertificateById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewCertificate(@RequestBody CertificateDTO certificateDTO) {
        Certificate certificate = CertificateMapper.mapper.certificateDTOtoCertificate(certificateDTO);
        certificateService.add(certificate);
        return ResponseEntity.ok(certificateDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCertificateById(@RequestBody CertificateDTO certificateDTO, @PathVariable("id") long id) {
        certificateService.edit(CertificateMapper.mapper.certificateDTOtoCertificate(certificateDTO), id);
        return ResponseEntity.ok(certificateDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCertificateById(@PathVariable(value = "id") long id) {
        certificateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

