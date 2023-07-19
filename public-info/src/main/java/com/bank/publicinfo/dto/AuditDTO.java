package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.Audit;
import io.micrometer.core.lang.Nullable;
import lombok.*;

import java.sql.Timestamp;

/**
 * DTO класс для {@link Audit}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    private String entityType;
    private String operationType;
    private String createdBy;
    @Nullable
    private String modifiedBy;
    private Timestamp createdAt;
    @Nullable
    private Timestamp modifiedAt;
    @Nullable
    private String newEntityJson;
    @Nullable
    private String entityJson;
}
