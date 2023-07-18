package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.exception.DataNotFoundException;
import com.bank.publicinfo.repository.BranchDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * Service для {@link Branch}
 */
@Service
@Validated
public class BranchServiceImpl implements BranchService {
    private final BranchDAO branchDAO;
    @Autowired
    public BranchServiceImpl(BranchDAO branchDAO) {this.branchDAO = branchDAO;}
    @Override
    public List<Branch> allBranch() {
        return branchDAO.findAll();
    }

    @Override
    public void add(Branch branch) {
        branchDAO.save(branch);
    }

    @Override
    public void delete(long id) {
        branchDAO.deleteById(id);
    }

    @Override
    public void edit(Branch branch, long id) {
        branch.setId(id);
        branchDAO.save(branch);
    }

    @Override
    public Branch getBranchById(long id) {
        Optional<Branch> newBranch = branchDAO.findById(id);
        return newBranch.orElseThrow(() -> new DataNotFoundException("Филиал по id " + id + " не найден!"));
    }
}
