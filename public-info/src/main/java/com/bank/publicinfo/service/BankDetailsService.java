package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;

import java.util.List;

public interface BankDetailsService {
    List<BankDetails> allBankDetails();

    void add(BankDetails bankDetails);

    void delete(long id);

    void edit(BankDetails bankDetails, long id);

    BankDetails getBankDetailsById(long id);
}
