package com.bank.publicinfo.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Entity класс для таблицы bank_details
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "bank_details", schema = "public_bank_information")
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bik", unique = true)
    private long bik;

    @Column(name = "inn", unique = true)
    private long inn;

    @Column(name = "kpp", unique = true)
    private long kpp;

    @Column(name = "cor_account", unique = true)
    private int corAccount;

    @Column(name = "city", columnDefinition = "varchar(180)")
    private String city;

    @Column(name = "joint_stock_company", columnDefinition = "varchar(15)")
    private String jointStockCompany;

    @Column(name = "name", columnDefinition = "varchar(80)")
    private String name;

    @Override
    public String toString() {
        return "{\n" +
                "    \"id\": " + id + ",\n" +
                "    \"bik\": " + bik + ",\n" +
                "    \"inn\": " + inn + ",\n" +
                "    \"kpp\": " + kpp + ",\n" +
                "    \"corAccount\": " + corAccount + "\",\n" +
                "    \"city\": \"" + city + "\n" +
                "    \"jointStockCompany\": \"" + jointStockCompany + "\",\n" +
                "    \"name\": \"" + name + "\"\n" +
                "}";
    }
}
