package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.exception.DataNotFoundException;
import com.bank.publicinfo.repository.BankDetailsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service для {@link BankDetails}
 */

@Service
public class BankDetailsServiceImpl implements BankDetailsService {
    private final BankDetailsDAO bankDetailsDAO;
    @Autowired
    public BankDetailsServiceImpl(BankDetailsDAO bankDetailsDAO) {this.bankDetailsDAO = bankDetailsDAO;}
    @Override
    public List<BankDetails> allBankDetails() {
        return bankDetailsDAO.findAll();
    }

    @Override
    public void add(BankDetails bankDetails) {
        bankDetailsDAO.save(bankDetails);
    }

    @Override
    public void delete(long id) {
        bankDetailsDAO.deleteById(id);
    }

    @Override
    public void edit(BankDetails bankDetails, long id) {
        bankDetails.setId(id);
        bankDetailsDAO.save(bankDetails);
    }

    @Override
    public BankDetails getBankDetailsById(long id) {
        Optional<BankDetails> newBankDetails = bankDetailsDAO.findById(id);
        return newBankDetails.orElseThrow(() -> new DataNotFoundException("Банк по id " + id + " не найден!"));
    }
}
