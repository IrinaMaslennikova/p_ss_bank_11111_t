package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.AtmDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtmServiceImplTest {
    @Mock
    private AtmDAO atmDAOMock;

    @InjectMocks
    private AtmServiceImpl service;

    Atm atm1 = Atm.builder().id(1).address("address").startOfWork(new Time(0L)).endOfWork(new Time(0L)).allHours(true)
            .branch(new Branch()).build();
    Atm atm2 = Atm.builder().id(2).address("address").startOfWork(new Time(0L)).endOfWork(new Time(0L)).allHours(true)
            .branch(new Branch()).build();

    @Test
    void allAtms() {
        List<Atm> data = List.of(atm1, atm2);

        when(atmDAOMock.findAll()).thenReturn(data);

        List<Atm> result = service.allAtms();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void add() {
        Atm atm = Atm.builder().id(3L).address("a").startOfWork(new Time(0L)).endOfWork(new Time(0L)).allHours(false)
                .branch(new Branch()).build();

        service.add(atm);

        verify(atmDAOMock).save(atm);
    }

    @Test
    void delete() {
        service.delete(1L);

        verify(atmDAOMock).deleteById(1L);
    }

    @Test
    void edit() {
        Atm newAtm = Atm.builder().id(2L).address("a").startOfWork(new Time(0L)).endOfWork(new Time(0L)).allHours(false)
                .branch(new Branch()).build();

        service.edit(newAtm, newAtm.getId());

        verify(atmDAOMock).save(newAtm);
    }

    @Test
    void getAtmById() {
        List<Atm> data = List.of(atm1, atm2);
        long id = 1L;

        when(atmDAOMock.findById(id)).thenReturn(Optional.of(data.get((int) id - 1)));

        Optional<Atm> result = Optional.ofNullable(service.getAtmById(id));

        assertNotNull(result.get());
        assertEquals(1L, result.get().getId());
    }
}