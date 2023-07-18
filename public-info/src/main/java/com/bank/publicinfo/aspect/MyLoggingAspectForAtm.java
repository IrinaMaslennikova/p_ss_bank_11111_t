package com.bank.publicinfo.aspect;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.repository.AuditDAO;
import com.bank.publicinfo.service.AtmService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

// Заполнение аудита по Atm

@Component
@AllArgsConstructor
@Aspect
public class MyLoggingAspectForAtm {
    private final AuditDAO auditDAO;
    private final AtmService atmService;

    // Метод для запонения аудита по созданию Atm

    @Around("execution (* com.bank.publicinfo.service.AtmServiceImpl.add(..))")
    public  Object aroundCreate(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Object targetMethodResult = jp.proceed();
        Atm entity = (Atm)jp.getArgs()[0];
        Audit audit = new Audit();
        audit.setOperationType("CREATE"); // готово
        audit.setCreatedBy(principalName); //готово
        audit.setCreatedAt(new Timestamp((new Date()).getTime())); //готово
        audit.setEntityJson(entity.toString()); //готово
        audit.setNewEntityJson(entity.toString()); //готово
        audit.setEntityType("Atm"); //готово
        auditDAO.save(audit);
        return targetMethodResult;
    }

    // Метод для запонения аудита по изменению Atm

    @Around("execution (* com.bank.publicinfo.service.AtmServiceImpl.edit(..))")
    public Object aroundUpdate(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Atm entity = (Atm) jp.getArgs()[0];
        long id = (long) jp.getArgs()[1];
        entity.setId(id);
        Atm oldEntity = atmService.getAtmById(id);
        Audit oldAudit = auditDAO.findAuditByNewEntityJson(oldEntity.toString());
        Audit audit = new Audit();
        audit.setEntityType("Atm");
        audit.setOperationType("UPDATE");
        audit.setModifiedAt(new Timestamp((new Date()).getTime()));
        audit.setModifiedBy(principalName);
        audit.setNewEntityJson(entity.toString());
        audit.setEntityJson(oldAudit.getNewEntityJson());
        audit.setCreatedAt(oldAudit.getCreatedAt());
        audit.setCreatedBy(oldAudit.getCreatedBy());
        auditDAO.save(audit);
        Object targetMethodResult = jp.proceed();
        return targetMethodResult;
    }

    // Метод для запонения аудита по удалению Atm

    @Around("execution (* com.bank.publicinfo.service.AtmServiceImpl.delete(..))")
    public  Object aroundDelete(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        long id = (long) jp.getArgs()[0];
        Atm oldEntity = atmService.getAtmById(id);
        Audit oldAudit = auditDAO.findAuditByNewEntityJson(oldEntity.toString());
        Object targetMethodResult = jp.proceed();
        Audit audit = new Audit();
        audit.setEntityType("Atm");
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
