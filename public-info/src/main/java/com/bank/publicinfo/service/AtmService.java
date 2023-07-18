package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;

import java.util.List;

public interface AtmService {
    List<Atm> allAtms();

    void add(Atm atm);

    void delete(long id);

    void edit(Atm atm, long id);

    Atm getAtmById(long id);
}
