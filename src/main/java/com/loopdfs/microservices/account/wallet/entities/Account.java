package com.loopdfs.microservices.account.wallet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "iban")
    private String iban;
    @Column(name = "bic_swift")
    private String bicSwift;
    @Column(name = "client_id")
    private Long clientId;
}
