package com.example.test.steven.cruz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.test.steven.cruz.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

	@Query(value="select c.account_initial_amount from tbl_accounts c where c.account_id = ?1", nativeQuery = true)
	Double findInitialAmountByAccountId(Integer accountId);
}
