package com.bank.publicinfo.entity;

import com.bank.publicinfo.validator.ValidPhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Time;

/**
 * Entity класс для таблицы branch
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "branch", schema = "public_bank_information")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address", columnDefinition = "varchar(370)")
    private String address;

    @Column(name = "phone_number", unique = true)
    @ValidPhoneNumber
    private Long phoneNumber;

    @Column(name = "city", columnDefinition = "varchar(250)")
    private String city;

    @Column(name = "start_of_work")
    @Nullable
    private Time startOfWork;

    @Column(name = "end_of_work")
    @Nullable
    private Time endOfWork;

    @Override
    public String toString() {
        return "{\n" +
                "    \"id\": \"" + id + "\",\n" +
                "    \"address\": \"" + address + "\",\n" +
                "    \"phoneNumber\": " + phoneNumber + ",\n" +
                "    \"city\": \"" + city + "\",\n" +
                "    \"startOfWork\": \"" + startOfWork + "\",\n" +
                "    \"endOfWork\": \"" + endOfWork + "\"\n" +
                "}";
    }
}

