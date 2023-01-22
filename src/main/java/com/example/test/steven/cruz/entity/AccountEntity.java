package com.example.test.steven.cruz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_accounts")
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer idAccount;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "account_initial_amount")
	private Double   initialAmount;
	
	@Column(name = "account_status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "account_client")
	private ClientEntity client;
}
