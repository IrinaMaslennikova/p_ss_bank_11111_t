package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.entity.License;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO класс для {@link License}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseDTO {
    private byte[] photo;
    private BankDetails bankDetails;
}

