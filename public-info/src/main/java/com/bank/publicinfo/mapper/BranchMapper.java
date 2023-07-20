package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper для {@link Branch}
 */

@Mapper
public interface BranchMapper {
    BranchMapper mapper = Mappers.getMapper(BranchMapper.class);

    @Mapping(target = "id", ignore = true)
    Branch branchDTOtoBranch(BranchDTO branchDTO);

    BranchDTO branchToBranchDTO(Branch branch);
}

