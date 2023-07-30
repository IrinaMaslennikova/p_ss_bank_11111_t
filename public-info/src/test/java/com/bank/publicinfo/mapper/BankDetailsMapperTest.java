package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class BankDetailsMapperTest {

    BankDetailsMapper mapper = Mappers.getMapper(BankDetailsMapper.class);

    @Test
    void bankDetailsDTOtoBankDetails() {
        BankDetailsDTO bankDetailsDTO = new BankDetailsDTO(1L, 1L, 1L, 123456, "city", "joint_stock_company", "name");
        BankDetails bankDetails = mapper.bankDetailsDTOtoBankDetails(bankDetailsDTO);
        BankDetailsDTO bankDetailsDTONull = null;
        BankDetails bankDetailsNull = mapper.bankDetailsDTOtoBankDetails(bankDetailsDTONull);
        assertEquals("Unexpected value found!", bankDetailsNull, null);
        assertEquals("Unexpected value found!", bankDetailsDTO.getBik(), bankDetails.getBik());
        assertEquals("Unexpected value found!", bankDetailsDTO.getInn(), bankDetails.getInn());
        assertEquals("Unexpected value found!", bankDetailsDTO.getKpp(), bankDetails.getKpp());
        assertEquals("Unexpected value found!", bankDetailsDTO.getCorAccount(), bankDetails.getCorAccount());
        assertEquals("Unexpected value found!", bankDetailsDTO.getCity(), bankDetails.getCity());
        assertEquals("Unexpected value found!", bankDetailsDTO.getJointStockCompany(), bankDetails.getJointStockCompany());
        assertEquals("Unexpected value found!", bankDetailsDTO.getName(), bankDetails.getName());
    }

    @Test
    void bankDetailsToBankDetailsDTO() {
        BankDetails bankDetails = new BankDetails(1, 1L, 1L, 1L, 123456, "city", "joint_stock_company", "name");
        BankDetailsDTO bankDetailsDTO = mapper.bankDetailsToBankDetailsDTO(bankDetails);
        BankDetails bankDetailsNull = null;
        BankDetailsDTO bankDetailsDTONull = mapper.bankDetailsToBankDetailsDTO(bankDetailsNull);
        assertEquals("Unexpected value found!", bankDetailsDTONull, null);
        assertEquals("Unexpected value found!", bankDetailsDTO.getBik(), bankDetails.getBik());
        assertEquals("Unexpected value found!", bankDetailsDTO.getInn(), bankDetails.getInn());
        assertEquals("Unexpected value found!", bankDetailsDTO.getKpp(), bankDetails.getKpp());
        assertEquals("Unexpected value found!", bankDetailsDTO.getCorAccount(), bankDetails.getCorAccount());
        assertEquals("Unexpected value found!", bankDetailsDTO.getCity(), bankDetails.getCity());
        assertEquals("Unexpected value found!", bankDetailsDTO.getJointStockCompany(), bankDetails.getJointStockCompany());
        assertEquals("Unexpected value found!", bankDetailsDTO.getName(), bankDetails.getName());
    }
}