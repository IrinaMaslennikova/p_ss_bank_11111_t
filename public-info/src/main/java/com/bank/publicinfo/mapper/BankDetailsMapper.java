package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
/**
 * Mapper для {@link BankDetails}
 */

@Mapper
public interface BankDetailsMapper {
    BankDetailsMapper mapper = Mappers.getMapper(BankDetailsMapper.class);

    @Mapping(target = "id", ignore = true)
    BankDetails bankDetailsDTOtoBankDetails(BankDetailsDTO bankDetailsDTO);

    BankDetailsDTO bankDetailsToBankDetailsDTO(BankDetails bankDetails);
}

