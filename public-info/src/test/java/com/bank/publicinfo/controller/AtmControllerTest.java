package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.service.AtmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Time;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AtmControllerTest {

    @Mock
    private final AtmMapper mapper = Mappers.getMapper(AtmMapper.class);
    @Mock
    AtmService service;
    @InjectMocks
    AtmController controller;

    Atm atm = Atm.builder().id(1L).address("address").startOfWork(new Time(0L)).endOfWork(new Time(0L)).allHours(true)
            .branch(new Branch()).build();
    AtmDTO atmDTO = mapper.atmToAtmDTO(atm);
    @Test
    void getAllAtm() {
        List<Atm> data = List.of(atm);
        when(service.allAtms()).thenReturn(data);

        ResponseEntity<List<AtmDTO>> response = controller.getAllAtm();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAtmById() {
        when(service.getAtmById(1)).thenReturn(atm);

        ResponseEntity<AtmDTO> response = controller.getAtmById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void addNewAtm() {
        ResponseEntity<AtmDTO> response = (ResponseEntity<AtmDTO>) controller.addNewAtm(atmDTO);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateAtmById() {
        ResponseEntity<AtmDTO> response = (ResponseEntity<AtmDTO>) controller.updateAtmById(atmDTO, 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteAtmById() {
        ResponseEntity<AtmDTO> response = (ResponseEntity<AtmDTO>) controller.deleteAtmById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}