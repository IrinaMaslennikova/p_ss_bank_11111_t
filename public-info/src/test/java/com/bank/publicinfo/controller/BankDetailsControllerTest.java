package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.service.BankDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankDetailsControllerTest {

    @Mock
    private final BankDetailsMapper mapper = Mappers.getMapper(BankDetailsMapper.class);
    @Mock
    BankDetailsService service;
    @InjectMocks
    BankDetailsController controller;
    BankDetails bankDetails = BankDetails.builder().id(1L).bik(0L).inn(0L).kpp(0L).corAccount(1234).city("city")
            .jointStockCompany("joint_stock_company").name("BANK1").build();
    BankDetailsDTO bankDetailsDTO = mapper.bankDetailsToBankDetailsDTO(bankDetails);
    @Test
    void getAllBankDetails() {
        List<BankDetails> data = List.of(bankDetails);
        when(service.allBankDetails()).thenReturn(data);

        ResponseEntity<List<BankDetailsDTO>> response = controller.getAllBankDetails();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getBankDetailsById() {
        when(service.getBankDetailsById(1)).thenReturn(bankDetails);

        ResponseEntity<BankDetailsDTO> response = controller.getBankDetailsById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void addNewBankDetails() {
        ResponseEntity<BankDetailsDTO> response = (ResponseEntity<BankDetailsDTO>) controller.addNewBankDetails(bankDetailsDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateBankDetailsById() {
        ResponseEntity<BankDetailsDTO> response = (ResponseEntity<BankDetailsDTO>) controller.updateBankDetailsById(bankDetailsDTO, 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteBankDetailsById() {
        ResponseEntity<BankDetailsDTO> response = (ResponseEntity<BankDetailsDTO>) controller.deleteBankDetailsById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}