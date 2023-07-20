package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.validator.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

/**
 * DTO класс для {@link Branch}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
    private String address;
    @ValidPhoneNumber
    private Long phoneNumber;
    private String city;
    private Time startOfWork;
    private Time endOfWork;
}

