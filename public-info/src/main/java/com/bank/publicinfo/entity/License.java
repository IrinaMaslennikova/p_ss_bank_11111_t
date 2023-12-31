package com.bank.publicinfo.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Entity класс для таблицы license
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "license", schema = "public_bank_information")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "photo")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "bank_details_id")
    private BankDetails bankDetails;

    @Override
    public String toString() {
        return "{\n" +
                "    \"id\": " + id + ",\n" +
                "    \"photo\": \"" + photo + "\",\n" +
                "    \"bankDetails\": {\n" +
                "           " + bankDetails.toString() + "\n" +
                "     }\n" +
                "}";
    }
}

