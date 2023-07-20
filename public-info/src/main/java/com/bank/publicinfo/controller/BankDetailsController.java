package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.service.BankDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-контроллер для таблицы bank_details сущности {@link BankDetails}
 */

@RestController
@AllArgsConstructor
@RequestMapping("/bank_details")
public class BankDetailsController {
    private final BankDetailsService bankDelailsService;

    @GetMapping
    public ResponseEntity<List<BankDetailsDTO>> getAllBankDetails() {
        return new ResponseEntity<>(bankDelailsService.allBankDetails().stream().map(bankDetails -> BankDetailsMapper.mapper.bankDetailsToBankDetailsDTO(bankDetails))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BankDetailsDTO> getBankDetailsById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(BankDetailsMapper.mapper.bankDetailsToBankDetailsDTO(bankDelailsService.getBankDetailsById(id)), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addNewBankDetails(@RequestBody BankDetailsDTO bankDetailsDTO) {
        BankDetails bankDetails = BankDetailsMapper.mapper.bankDetailsDTOtoBankDetails(bankDetailsDTO);
        bankDelailsService.add(bankDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBankDetailsById(@RequestBody BankDetailsDTO bankDetailsDTO, @PathVariable("id") long id) {
        bankDelailsService.edit(BankDetailsMapper.mapper.bankDetailsDTOtoBankDetails(bankDetailsDTO), id);
        return ResponseEntity.ok(bankDetailsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBankDetailsById(@PathVariable(value = "id") long id) {
        bankDelailsService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

