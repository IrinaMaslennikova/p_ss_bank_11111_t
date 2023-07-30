/*
package com.bank.publicinfo.oldaspect;

import com.bank.publicinfo.entity.Audit;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.AuditDAO;
import com.bank.publicinfo.service.BranchService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

// Заполнение аудита по Branch

@Component
@Aspect
@AllArgsConstructor
public class MyLoggingAspectForBranch {
    private final AuditDAO auditDAO;
    private final BranchService branchService;

    // Метод для запонения аудита по созданию Branch

    @Around("execution (* com.bank.publicinfo.service.BranchServiceImpl.add(..))")
    public  Object aroundCreate(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Object targetMethodResult = jp.proceed();
        Branch entity = (Branch) jp.getArgs()[0];
        Audit audit = new Audit();
        audit.setOperationType("CREATE"); // готово
        audit.setCreatedBy(principalName); //готово
        audit.setCreatedAt(new Timestamp((new Date()).getTime())); //готово
        audit.setEntityJson(entity.toString()); //готово
        audit.setNewEntityJson(entity.toString()); //готово
        audit.setEntityType("Branch"); //готово
        auditDAO.save(audit);
        return targetMethodResult;
    }

    // Метод для запонения аудита по изменению Branch

    @Around("execution (* com.bank.publicinfo.service.BranchServiceImpl.edit(..))")
    public Object aroundUpdate(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        Branch entity = (Branch) jp.getArgs()[0];
        long id = (long) jp.getArgs()[1];
        entity.setId(id);
        Branch oldEntity = branchService.getBranchById(id);
        Audit oldAudit = auditDAO.findAuditByNewEntityJson(oldEntity.toString());
        Object targetMethodResult = jp.proceed();
        Audit audit = new Audit();
        audit.setEntityType("Branch");
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

    // Метод для запонения аудита по удалению Branch

    @Around("execution (* com.bank.publicinfo.service.BranchServiceImpl.delete(..))")
    public Object aroundDelete(ProceedingJoinPoint jp) throws Throwable {
        String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
        long id = (long) jp.getArgs()[0];
        Branch oldEntity = branchService.getBranchById(id);
        Audit oldAudit = auditDAO.findAuditByNewEntityJson(oldEntity.toString());
        Object targetMethodResult = jp.proceed();
        Audit audit = new Audit();
        audit.setEntityType("Branch");
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

*/
