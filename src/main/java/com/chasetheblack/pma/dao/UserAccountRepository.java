package com.chasetheblack.pma.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.chasetheblack.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long>{

}
