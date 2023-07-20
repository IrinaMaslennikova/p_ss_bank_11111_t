package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.exception.ValidatorException;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-контроллер для таблицы branch сущности {@link Branch}
 */

@RestController
@AllArgsConstructor
@RequestMapping("/branch")
public class BranchController {
    private final BranchService branchService;
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranch() {
        return new ResponseEntity<>(branchService.allBranch().stream().map(branch -> BranchMapper.mapper.branchToBranchDTO(branch))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(BranchMapper.mapper.branchToBranchDTO(branchService.getBranchById(id)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addNewBranch(@Valid @RequestBody BranchDTO branchDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidatorException("Номер телефона должен начинаться на '8' и состоять из 11 цифр!");
        }
        Branch branch = BranchMapper.mapper.branchDTOtoBranch(branchDTO);
        branchService.add(branch);
        return ResponseEntity.ok(branchDTO);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBranchById(@RequestBody BranchDTO branchDTO, @PathVariable("id") long id) {
        branchService.edit(BranchMapper.mapper.branchDTOtoBranch(branchDTO), id);
        return ResponseEntity.ok(branchDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranchById(@PathVariable(value = "id") long id) {
        branchService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

