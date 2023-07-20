package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Branch;

import java.util.List;

public interface BranchService {
    List<Branch> allBranch();

    void add(Branch branch);

    void delete(long id);

    void edit(Branch branch, long id);

    Branch getBranchById(long id);
}

