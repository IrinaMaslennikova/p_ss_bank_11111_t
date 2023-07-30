package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.BankDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO класс для {@link BankDetails}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetailsDTO {
    private long bik;
    private long inn;
    private long kpp;
    private int corAccount;
    private String city;
    private String jointStockCompany;
    private String name;
}

