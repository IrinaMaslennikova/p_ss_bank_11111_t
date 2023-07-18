package com.bank.publicinfo.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Time;

/**
 * Entity класс для таблицы atm
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "atm", schema = "public_bank_information")
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address", columnDefinition = "varchar(370)")
    private String address;

    @Column(name = "start_of_work")
    @Nullable
    private Time startOfWork;

    @Column(name = "end_of_work")
    @Nullable
    private Time endOfWork;

    @Column(name = "all_hours")
    private boolean allHours;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    @Nullable
    private Branch branch;

    public boolean getAllHours() {
        return allHours;
    }

    @Override
    public String toString() {
        return "{\n" +
                "    \"id\": " + id + ",\n" +
                "    \"address\": \"" + address + "\",\n" +
                "    \"startOfWork\": " + startOfWork + ",\n" +
                "    \"endOfWork\": \"" + endOfWork + "\",\n" +
                "    \"allHours\": " + allHours + "\n" +
                "    \"branch\": {\n" +
                "           " + branch.toString() + "\n" +
                "     }\n" +
                "}";
    }
}
