package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AuditDTO;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.mapper.AuditMapper;
import com.bank.publicinfo.service.AuditService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-контроллер для таблицы audit сущности {@link Audit}
 */


@RestController
@AllArgsConstructor
@RequestMapping("/audit")
public class AuditController {
    private final AuditService auditService;

    @GetMapping
    public ResponseEntity<List<AuditDTO>> getAllAudit() {
        return new ResponseEntity<>(auditService.allAudit().stream().map(audit -> AuditMapper.mapper.auditToAuditDTO(audit))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
}
