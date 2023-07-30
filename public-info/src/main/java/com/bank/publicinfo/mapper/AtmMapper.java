package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper для {@link Atm}
 */

@Mapper
public interface AtmMapper {

    AtmMapper mapper = Mappers.getMapper(AtmMapper.class);

    @Mapping(target = "id", ignore = true)

    Atm atmDTOtoAtm(AtmDTO atmDTO);

    AtmDTO atmToAtmDTO(Atm atm);
}
