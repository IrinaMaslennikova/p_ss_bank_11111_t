package com.bank.publicinfo.aspect;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.repository.AuditDAO;
import com.bank.publicinfo.service.CertificateService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

// Заполнение аудита по Certificate

@Component
@Aspect
@AllArgsConstructor
public class MyLoggingAspectForCertificate {
    private final AuditDAO auditDAO;
    private final CertificateService certificateService;

    // Метод для запонения аудита по созданию Certificate

    @Around("execution (* com.bank.publicinfo.service.CertificateServiceImpl.add(..))")
    public  Object aroundCreate(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Object targetMethodResult = jp.proceed();
        Certificate entity = (Certificate) jp.getArgs()[0];
        Audit audit = new Audit();
        audit.setOperationType("CREATE"); // готово
        audit.setCreatedBy(principalName); //готово
        audit.setCreatedAt(new Timestamp((new Date()).getTime())); //готово
        audit.setEntityJson(entity.toString()); //готово
        audit.setNewEntityJson(entity.toString()); //готово
        audit.setEntityType("Certificate"); //готово
        auditDAO.save(audit);
        return targetMethodResult;
    }

    // Метод для запонения аудита по изменению Certificate

    @Around("execution (* com.bank.publicinfo.service.CertificateServiceImpl.edit(..))")
    public Object aroundUpdate(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Certificate entity = (Certificate) jp.getArgs()[0];
        long id = (long) jp.getArgs()[1];
        entity.setId(id);
        Certificate oldEntity = certificateService.getCertificateById(id);
        Audit oldAudit = auditDAO.findAuditByNewEntityJson(oldEntity.toString());
        Object targetMethodResult = jp.proceed();
        Audit audit = new Audit();
        audit.setEntityType("Certificate");
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

    // Метод для запонения аудита по удалению Certificate

    @Around("execution (* com.bank.publicinfo.service.CertificateServiceImpl.delete(..))")
    public  Object aroundDelete(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        long id = (long) jp.getArgs()[0];
        Certificate oldEntity = certificateService.getCertificateById(id);
        Audit oldAudit = auditDAO.findAuditByNewEntityJson(oldEntity.toString());
        Object targetMethodResult = jp.proceed();
        Audit audit = new Audit();
        audit.setEntityType("Certificate");
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

