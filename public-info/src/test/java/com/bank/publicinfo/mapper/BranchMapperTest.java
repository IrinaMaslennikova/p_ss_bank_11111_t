package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;

import static org.junit.Assert.assertEquals;

@ExtendWith(MockitoExtension.class)
class BranchMapperTest {
    BranchMapper mapper = Mappers.getMapper(BranchMapper.class);

    @Test
    void branchDTOtoBranch() {
        BranchDTO branchDTO = new BranchDTO("address", 89520015799L, "city", new Time(0L), new Time(0L));
        Branch branch = mapper.branchDTOtoBranch(branchDTO);
        BranchDTO branchDTONull = null;
        Branch branchNull = mapper.branchDTOtoBranch(branchDTONull);
        assertEquals("Unexpected value found!", branchNull, null);
        assertEquals("Unexpected value found!", branchDTO.getAddress(), branch.getAddress());
        assertEquals("Unexpected value found!", branchDTO.getPhoneNumber(), branch.getPhoneNumber());
        assertEquals("Unexpected value found!", branchDTO.getCity(), branch.getCity());
        assertEquals("Unexpected value found!", branchDTO.getStartOfWork(), branch.getStartOfWork());
        assertEquals("Unexpected value found!", branchDTO.getEndOfWork(), branch.getEndOfWork());
    }

    @Test
    void branchToBranchDTO() {
        Branch branch = new Branch(1, "address", 89520015799L, "city", new Time(0L), new Time(0L));
        BranchDTO branchDTO = mapper.branchToBranchDTO(branch);
        Branch branchNull = null;
        BranchDTO branchDTONull = mapper.branchToBranchDTO(branchNull);
        assertEquals("Unexpected value found!", branchDTONull, null);
        assertEquals("Unexpected value found!", branchDTO.getAddress(), branch.getAddress());
        assertEquals("Unexpected value found!", branchDTO.getPhoneNumber(), branch.getPhoneNumber());
        assertEquals("Unexpected value found!", branchDTO.getCity(), branch.getCity());
        assertEquals("Unexpected value found!", branchDTO.getStartOfWork(), branch.getStartOfWork());
        assertEquals("Unexpected value found!", branchDTO.getEndOfWork(), branch.getEndOfWork());
    }
}