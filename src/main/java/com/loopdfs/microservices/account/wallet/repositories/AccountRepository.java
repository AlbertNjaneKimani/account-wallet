package com.loopdfs.microservices.account.wallet.repositories;

import com.loopdfs.microservices.account.wallet.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
