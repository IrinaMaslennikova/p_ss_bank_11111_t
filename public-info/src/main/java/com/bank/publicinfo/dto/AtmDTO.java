package com.bank.publicinfo.dto;

import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.sql.Time;

/**
 * DTO класс для {@link Atm}
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtmDTO {
    private String address;
    @Nullable
    private Time startOfWork;
    @Nullable
    private Time endOfWork;
    private boolean allHours;
    @Nullable
    private Branch branch;

    public boolean getAllHours() {
        return allHours;
    }
}

