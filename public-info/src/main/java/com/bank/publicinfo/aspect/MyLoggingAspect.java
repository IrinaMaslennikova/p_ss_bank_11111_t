package com.bank.publicinfo.aspect;

import com.bank.publicinfo.entity.*;
import com.bank.publicinfo.repository.AuditDAO;
import com.bank.publicinfo.service.*;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@AllArgsConstructor
@Aspect
public class MyLoggingAspect {
    private final AuditDAO auditDAO;
    private final AtmService atmService;
    private final BranchService branchService;
    private final BankDetailsService bankDetailsService;
    private final CertificateService certificateService;
    private final LicenseService licenseService;

    // Метод для запонения аудита по созданию Atm

    @Around("execution (* com.bank.publicinfo.service.*.add(..))")
    public  Object aroundCreate(ProceedingJoinPoint jp) throws Throwable {
        Object targetMethodResult = jp.proceed();
        auditDAO.save(Audit.builder().operationType("CREATE").createdAt(new Timestamp((new Date()).getTime())).createdBy(SecurityContextHolder.getContext().getAuthentication().getName()).entityJson(jp.getArgs()[0].toString()).newEntityJson(jp.getArgs()[0].toString()).entityType(jp.getArgs()[0].getClass().getName().substring(27)).build());
        return targetMethodResult;
    }

    // Метод для запонения аудита по изменению Atm

    @Around("execution (* com.bank.publicinfo.service.*.edit(..))")
    public Object aroundUpdate(ProceedingJoinPoint jp) throws Throwable {
        Object object = jp.getArgs()[0];
        long id = (long) jp.getArgs()[1];
        Audit oldAudit = new Audit();
        String newJson = null;
        if (object.getClass().getName().equals("com.bank.publicinfo.entity.Atm")) {
            Atm entity = (Atm) object;
            entity.setId(id);
            oldAudit = auditDAO.findAuditByNewEntityJson(atmService.getAtmById(id).toString());
            newJson = entity.toString();
        } else if (object.getClass().getName().equals("com.bank.publicinfo.entity.Branch")) {
            Branch entity = (Branch) object;
            entity.setId(id);
            oldAudit = auditDAO.findAuditByNewEntityJson(branchService.getBranchById(id).toString());
            newJson = entity.toString();
        } else if (object.getClass().getName().equals("com.bank.publicinfo.entity.BankDetails")) {
            BankDetails entity = (BankDetails) object;
            entity.setId(id);
            oldAudit = auditDAO.findAuditByNewEntityJson(bankDetailsService.getBankDetailsById(id).toString());
            newJson = entity.toString();
        } else if (object.getClass().getName().equals("com.bank.publicinfo.entity.Certificate")) {
            Certificate entity = (Certificate) object;
            entity.setId(id);
            oldAudit = auditDAO.findAuditByNewEntityJson(certificateService.getCertificateById(id).toString());
            newJson = entity.toString();
        } else {
            License entity = (License) object;
            entity.setId(id);
            oldAudit = auditDAO.findAuditByNewEntityJson(licenseService.getLicenseById(id).toString());
            newJson = entity.toString();
        }
        Object targetMethodResult = jp.proceed();
        auditDAO.save(Audit.builder().entityType(object.getClass().getName().substring(27)).operationType("UPDATE").createdAt(oldAudit.getCreatedAt()).createdBy(oldAudit.getCreatedBy()).modifiedBy(SecurityContextHolder.getContext().getAuthentication().getName()).modifiedAt(new Timestamp((new Date()).getTime())).newEntityJson(newJson).entityJson(oldAudit.getNewEntityJson()).build());
        return targetMethodResult;
    }

    // Метод для запонения аудита по удалению Atm

    @Around("execution (* com.bank.publicinfo.service.*.delete(..))")
    public  Object aroundDelete(ProceedingJoinPoint jp) throws Throwable {
        long id = (long) jp.getArgs()[0];
        Audit oldAudit = null;
        String name = new Throwable().getStackTrace()[15].getClassName();
        if (name.equals("com.bank.publicinfo.controller.AtmController")) {
            oldAudit = auditDAO.findAuditByNewEntityJson(atmService.getAtmById(id).toString());
        } else if (name.equals("com.bank.publicinfo.controller.BranchController")) {
            oldAudit = auditDAO.findAuditByNewEntityJson(branchService.getBranchById(id).toString());
        } else if (name.equals("com.bank.publicinfo.controller.BankDetailsController")) {
            oldAudit = auditDAO.findAuditByNewEntityJson(bankDetailsService.getBankDetailsById(id).toString());
        } else if (name.equals("com.bank.publicinfo.controller.CertificateController")) {
            oldAudit = auditDAO.findAuditByNewEntityJson(certificateService.getCertificateById(id).toString());
        } else {
            oldAudit = auditDAO.findAuditByNewEntityJson(licenseService.getLicenseById(id).toString());
        }
        Object targetMethodResult = jp.proceed();
        auditDAO.save(Audit.builder().entityType(oldAudit.getEntityType()).operationType("DELETE").modifiedAt(new Timestamp((new Date()).getTime())).modifiedBy(SecurityContextHolder.getContext().getAuthentication().getName()).entityJson(oldAudit.getNewEntityJson()).newEntityJson(null).createdBy(oldAudit.getCreatedBy()).createdAt(oldAudit.getCreatedAt()).build());
        return targetMethodResult;
    }
}


