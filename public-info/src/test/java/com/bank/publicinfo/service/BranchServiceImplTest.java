package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.repository.BranchDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchServiceImplTest {

    @Mock
    private BranchDAO branchDAOMock;

    @InjectMocks
    private BranchServiceImpl service;

    Branch branch1 = Branch.builder().id(1L).address("address").city("city").phoneNumber(89520025799L).startOfWork(new Time(0L))
            .endOfWork(new Time(0L)).build();
    Branch branch2 = Branch.builder().id(2L).address("address").city("city").phoneNumber(89520025798L).startOfWork(new Time(0L))
            .endOfWork(new Time(0L)).build();

    @Test
    void allBranch() {
        List<Branch> data = List.of(branch1, branch2);

        when(branchDAOMock.findAll()).thenReturn(data);

        List<Branch> result = service.allBranch();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void add() {
        Branch branch = Branch.builder().id(3L).address("address").city("city").phoneNumber(89520025797L).startOfWork(new Time(0L))
                .endOfWork(new Time(0L)).build();

        service.add(branch);

        verify(branchDAOMock).save(branch);
    }

    @Test
    void delete() {
        service.delete(1L);

        verify(branchDAOMock).deleteById(1L);
    }

    @Test
    void edit() {
        Branch newBranch = Branch.builder().id(2L).address("new_address").city("city").phoneNumber(89520025000L).startOfWork(new Time(0L))
                .endOfWork(new Time(0L)).build();

        service.edit(newBranch, newBranch.getId());

        verify(branchDAOMock).save(newBranch);
    }

    @Test
    void getBranchById() {
        List<Branch> data = List.of(branch1, branch2);
        long id = 1L;

        when(branchDAOMock.findById(id)).thenReturn(Optional.of(data.get((int) id - 1)));

        Optional<Branch> result = Optional.ofNullable(service.getBranchById(id));

        assertNotNull(result.get());
        assertEquals(1L, result.get().getId());
    }
}