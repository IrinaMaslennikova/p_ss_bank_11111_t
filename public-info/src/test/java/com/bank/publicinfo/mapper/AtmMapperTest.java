package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class AtmMapperTest {
    AtmMapper mapper = Mappers.getMapper(AtmMapper.class);
    Branch branch = new Branch();

    @Test
    void atmDTOtoAtm() {
        AtmDTO atmDTO = new AtmDTO("address", new Time(0L),  new Time(0L), true, branch);
        Atm atm = mapper.atmDTOtoAtm(atmDTO);
        AtmDTO atmDTONull = null;
        Atm atmNull = mapper.atmDTOtoAtm(atmDTONull);
        assertEquals("Unexpected value found!", atmNull, null);
        assertEquals("Unexpected value found!", atmDTO.getAddress(), atm.getAddress());
        assertEquals("Unexpected value found!", atmDTO.getStartOfWork(), atm.getStartOfWork());
        assertEquals("Unexpected value found!", atmDTO.getEndOfWork(), atm.getEndOfWork());
        assertEquals("Unexpected value found!", atmDTO.getAllHours(), atm.getAllHours());
        assertEquals("Unexpected value found!", atmDTO.getBranch(), atm.getBranch());
    }

    @Test
    void atmToAtmDTO() {
        Atm atm = new Atm(1, "address", new Time(0L),  new Time(0L), true, branch);
        AtmDTO atmDTO = mapper.atmToAtmDTO(atm);
        Atm atmNull = null;
        AtmDTO atmDTONull = mapper.atmToAtmDTO(atmNull);
        assertEquals("Unexpected value found!", atmNull, null);
        assertEquals("Unexpected value found!", atmDTO.getAddress(), atm.getAddress());
        assertEquals("Unexpected value found!", atmDTO.getStartOfWork(), atm.getStartOfWork());
        assertEquals("Unexpected value found!", atmDTO.getEndOfWork(), atm.getEndOfWork());
        assertEquals("Unexpected value found!", atmDTO.getAllHours(), atm.getAllHours());
        assertEquals("Unexpected value found!", atmDTO.getBranch(), atm.getBranch());
    }
}