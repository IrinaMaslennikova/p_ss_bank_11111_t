package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.repository.BankDetailsDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankDetailsServiceImplTest {

    @Mock
    private BankDetailsDAO bankDetailsDAOMock;

    @InjectMocks
    private BankDetailsServiceImpl service;

    BankDetails bankDetails1 = BankDetails.builder().id(1L).bik(0L).inn(0L).kpp(0L).corAccount(1234).city("city")
            .jointStockCompany("joint_stock_company").name("BANK1").build();
    BankDetails bankDetails2 = BankDetails.builder().id(2L).bik(1L).inn(1L).kpp(1L).corAccount(12345).city("city")
            .jointStockCompany("joint_stock_company").name("BANK2").build();
    @Test
    void allBankDetails() {
        List<BankDetails> data = List.of(bankDetails1, bankDetails2);

        when(bankDetailsDAOMock.findAll()).thenReturn(data);

        List<BankDetails> result = service.allBankDetails();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void add() {
        BankDetails bankDetails = BankDetails.builder().id(3L).bik(0L).inn(0L).kpp(0L).corAccount(1234).city("city")
                .jointStockCompany("joint_stock_company").name("BANK3").build();

        service.add(bankDetails);

        verify(bankDetailsDAOMock).save(bankDetails);
    }

    @Test
    void delete() {
        service.delete(1L);

        verify(bankDetailsDAOMock).deleteById(1L);
    }

    @Test
    void edit() {
        BankDetails newBankDetails = BankDetails.builder().id(2L).bik(0L).inn(0L).kpp(0L).corAccount(1234).city("city")
                .jointStockCompany("joint_stock_company").name("BANK3").build();

        service.edit(newBankDetails, newBankDetails.getId());

        verify(bankDetailsDAOMock).save(newBankDetails);
    }

    @Test
    void getBankDetailsById() {
        List<BankDetails> data = List.of(bankDetails1, bankDetails2);
        long id = 1L;

        when(bankDetailsDAOMock.findById(id)).thenReturn(Optional.of(data.get((int) id - 1)));

        Optional<BankDetails> result = Optional.ofNullable(service.getBankDetailsById(id));

        assertNotNull(result.get());
        assertEquals(1L, result.get().getId());
    }
}