package com.example.test.steven.cruz.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.test.steven.cruz.dto.CustomMovementResponse;
import com.example.test.steven.cruz.entity.MovementEntity;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Integer> {

	@Query(value ="select * from tbl_movements m where (m.movement_date >= ?1 and m.movement_date <= ?2) and m.movement_account = ?3", nativeQuery = true)
	List<MovementEntity> findMovementByDateAndAccountId(LocalDateTime initialDate, LocalDateTime finalDate, Integer accountId);

	@Query(value="select max(m.movement_id) from tbl_movements m where m.movement_account = ?1", nativeQuery = true)
	Integer findLastMovementIdByAccountId(Integer accountId);
	 
	@Query(value="select m.movement_balance from tbl_movements m where m.movement_id = ?1", nativeQuery = true)
	Double findLastBalanceByMovementId(Integer movementId);
	
	@Query(value="select sum(m.movement_value) from tbl_movements m where (m.movement_date >= ?1 and m.movement_date <= ?2) and m.movement_type = ?3 and m.movement_account = ?4", nativeQuery = true)
	Double findSumValueMovementByDateAndTypeAndAccountId(LocalDateTime initialDate, LocalDateTime finalDate, String movementType, Integer accountId);

	@Query(value ="select m.movement_day, "
			+ "			  c.name, "
			+ "			  a.account_number, a.account_type, a.account_initial_amount, a.account_status"
			+ "           m.movement_value, m.movement_balance"
			+ "    from tbl_movements m, tbl_accounts a, tbl_clients c "
			+ "    where m.movement_account = a.account_id "
			+ "		 and a.account_client = c.client_id"
			+ "		 and (m.movement_date >= ?1 and m.movement_date <= ?2) and c.client_id = ?3"
			+ "order by a.account_number, m.movement_day", nativeQuery = true)
	List<CustomMovementResponse> findMovementByDateAndClientId(LocalDateTime initialDate, LocalDateTime finalDate, Integer clientId);
	;
}
