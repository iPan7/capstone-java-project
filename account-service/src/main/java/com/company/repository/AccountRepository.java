package com.company.repository;

import com.company.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    List<Account> findByUsername(String username);
    List<Account> findByPassword(String password);

}
