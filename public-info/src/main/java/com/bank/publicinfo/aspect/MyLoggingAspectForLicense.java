package com.bank.publicinfo.aspect;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.repository.AuditDAO;
import com.bank.publicinfo.service.LicenseService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

// Заполнение аудита по Licence

@Component
@Aspect
@AllArgsConstructor
public class MyLoggingAspectForLicense {
    private final AuditDAO auditDAO;
    private final LicenseService licenseService;

    // Метод для запонения аудита по созданию License

    @Around("execution (* com.bank.publicinfo.service.LicenseServiceImpl.add(..))")
    public  Object aroundCreate(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Object targetMethodResult = jp.proceed();
        License entity = (License) jp.getArgs()[0];
        Audit audit = new Audit();
        audit.setOperationType("CREATE"); // готово
        audit.setCreatedBy(principalName); //готово
        audit.setCreatedAt(new Timestamp((new Date()).getTime())); //готово
        audit.setEntityJson(entity.toString()); //готово
        audit.setNewEntityJson(entity.toString()); //готово
        audit.setEntityType("License"); //готово
        auditDAO.save(audit);
        return targetMethodResult;
    }

    // Метод для запонения аудита по изменению License

    @Around("execution (* com.bank.publicinfo.service.LicenseServiceImpl.edit(..))")
    public Object aroundUpdate(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        License entity = (License) jp.getArgs()[0];
        long id = (long) jp.getArgs()[1];
        entity.setId(id);
        License oldEntity = licenseService.getLicenseById(id);
        Audit oldAudit = auditDAO.findAuditByNewEntityJson(oldEntity.toString());
        Object targetMethodResult = jp.proceed();
        Audit audit = new Audit();
        audit.setEntityType("License");
        audit.setOperationType("UPDATE");
        audit.setModifiedAt(new Timestamp((new Date()).getTime()));
        audit.setModifiedBy(principalName);
        audit.setNewEntityJson(entity.toString());
        audit.setEntityJson(oldAudit.getNewEntityJson());
        audit.setCreatedAt(oldAudit.getCreatedAt());
        audit.setCreatedBy(oldAudit.getCreatedBy());
        auditDAO.save(audit);
        return targetMethodResult;
    }

    // Метод для запонения аудита по удалению License

    @Around("execution (* com.bank.publicinfo.service.LicenseServiceImpl.delete(..))")
    public  Object aroundDelete(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        long id = (long) jp.getArgs()[0];
        License oldEntity = licenseService.getLicenseById(id);
        Audit oldAudit = auditDAO.findAuditByNewEntityJson(oldEntity.toString());
        Object targetMethodResult = jp.proceed();
        Audit audit = new Audit();
        audit.setEntityType("License");
        audit.setOperationType("DELETE");
        audit.setModifiedAt(new Timestamp((new Date()).getTime()));
        audit.setModifiedBy(principalName);
        audit.setNewEntityJson(null);
        audit.setEntityJson(oldAudit.getNewEntityJson());
        audit.setCreatedAt(oldAudit.getCreatedAt());
        audit.setCreatedBy(oldAudit.getCreatedBy());
        auditDAO.save(audit);
        return targetMethodResult;
    }
}

