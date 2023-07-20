package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.exception.DataNotFoundException;
import com.bank.publicinfo.repository.AtmDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service для {@link Atm}
 */

@Service
public class AtmServiceImpl implements AtmService {
    private final AtmDAO atmDAO;
    @Autowired
    public AtmServiceImpl(AtmDAO atmDAO) {this.atmDAO = atmDAO;}
    @Override
    public List<Atm> allAtms() {
        return atmDAO.findAll();
    }

    @Override
    public void add(Atm atm) {
        atmDAO.save(atm);
    }

    @Override
    public void delete(long id) {
        atmDAO.deleteById(id);
    }

    @Override
    public void edit(Atm atm, long id) {
        atm.setId(id);
        atmDAO.save(atm);
    }

    @Override
    public Atm getAtmById(long id) {
        Optional<Atm> atmById = atmDAO.findById(id);
        return atmById.orElseThrow(() -> new DataNotFoundException("Банкомат по id " + id + " не найден!"));
    }
}

