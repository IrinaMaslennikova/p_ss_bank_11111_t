package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.exception.ValidatorException;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.service.AtmService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST-контроллер для таблицы atm сущности {@link Atm}
 */

@RestController
@RequestMapping("/atm")
@AllArgsConstructor
public class AtmController {
    private final AtmService atmService;

    @GetMapping
    public ResponseEntity<List<AtmDTO>> getAllAtm() {
        return new ResponseEntity<>(atmService.allAtms().stream().map(atm -> AtmMapper.mapper.atmToAtmDTO(atm))
                .collect(Collectors.toList()), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AtmDTO> getAtmById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(AtmMapper.mapper.atmToAtmDTO(atmService.getAtmById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addNewAtm(@RequestBody AtmDTO atmDTO) throws ValidatorException {
        atmService.add(AtmMapper.mapper.atmDTOtoAtm(atmDTO));
        return ResponseEntity.ok(atmDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAtmById(@RequestBody AtmDTO atmDTO, @PathVariable("id") long id) {
        atmService.edit(AtmMapper.mapper.atmDTOtoAtm(atmDTO), id);
        return ResponseEntity.ok(atmDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAtmById(@PathVariable(value = "id") long id) {
        atmService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


