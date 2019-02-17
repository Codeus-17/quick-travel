/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travel.quicktravel.Repositories;

import com.travel.quicktravel.entities.Account;
import javax.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author PC
 */
public interface AccountRepository extends JpaRepository<Account, Id> {
    public Account findByEmail(String email);
}
