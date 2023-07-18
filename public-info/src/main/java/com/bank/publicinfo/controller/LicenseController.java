package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-контроллер для таблицы license сущности {@link License}
 */

@RestController
@AllArgsConstructor
@RequestMapping("/license")
public class LicenseController {
    private final LicenseService licenseService;

    @GetMapping
    public ResponseEntity<List<LicenseDTO>> getAllLicenses() {
        return new ResponseEntity<>(licenseService.allLicenses().stream().map(license -> LicenseMapper.mapper.licenseToLicenseDTO(license))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LicenseDTO> getLicenseById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(LicenseMapper.mapper.licenseToLicenseDTO(licenseService.getLicenseById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewLicense(@RequestBody LicenseDTO licenseDTO) {
        License license = LicenseMapper.mapper.licenseDTOtoLicense(licenseDTO);
        licenseService.add(license);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateLicenseById(@RequestBody LicenseDTO licenseDTO, @PathVariable("id") long id) {
        licenseService.edit(LicenseMapper.mapper.licenseDTOtoLicense(licenseDTO), id);
        return ResponseEntity.ok(licenseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLicenseById(@PathVariable(value = "id") long id) {
        licenseService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
