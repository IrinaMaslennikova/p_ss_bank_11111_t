package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificateDTO {
    private byte[] photo;
    private BankDetails bankDetails;
}

