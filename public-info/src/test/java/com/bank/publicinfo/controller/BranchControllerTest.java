package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.service.AtmService;
import com.bank.publicinfo.service.BranchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.sql.Time;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchControllerTest {
    @Mock
    private final BranchMapper mapper = Mappers.getMapper(BranchMapper.class);
    @Mock
    BranchService service;
    @InjectMocks
    BranchController controller;

    Branch branch = Branch.builder().id(1L).address("address").city("city").phoneNumber(89520025799L).startOfWork(new Time(0L))
            .endOfWork(new Time(0L)).build();
    BranchDTO branchDTO = mapper.branchToBranchDTO(branch);
    @Test
    void getAllBranch() {
        List<Branch> data = List.of(branch);
        when(service.allBranch()).thenReturn(data);

        ResponseEntity<List<BranchDTO>> response = controller.getAllBranch();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getBranchById() {
        when(service.getBranchById(1)).thenReturn(branch);

        ResponseEntity<BranchDTO> response = controller.getBranchById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void addNewBranch() {
        ResponseEntity<BranchDTO> response = (ResponseEntity<BranchDTO>) controller.addNewBranch(branchDTO, mock(BindingResult.class));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateBranchById() {
        ResponseEntity<BranchDTO> response = (ResponseEntity<BranchDTO>) controller.updateBranchById(branchDTO, 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteBranchById() {
        ResponseEntity<BranchDTO> response = (ResponseEntity<BranchDTO>) controller.deleteBranchById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}