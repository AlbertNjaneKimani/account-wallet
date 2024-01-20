package com.loopdfs.microservices.account.wallet.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Header {
    private int responseCode;
    private String customerMessage;
    private String technicalMessage;
}
