package com.bank.publicinfo.entity;

import io.micrometer.core.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Entity класс для таблицы audit
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "audit", schema = "public_bank_information")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "entity_type", columnDefinition = "varchar(40)")
    private String entityType;

    @Column(name = "operation_type", columnDefinition = "varchar(255)")
    private String operationType;

    @Column(name = "created_by", columnDefinition = "varchar(255)")
    private String createdBy;

    @Column(name = "modified_by", columnDefinition = "varchar(255)")
    @Nullable
    private String modifiedBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modified_at")
    @Nullable
    private Timestamp modifiedAt;

    @Column(name = "new_entity_json")
    @Nullable
    private String newEntityJson;

    @Column(name = "entity_json")
    private String entityJson;
}

