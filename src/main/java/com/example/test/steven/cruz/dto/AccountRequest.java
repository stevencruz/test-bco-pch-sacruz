package com.example.test.steven.cruz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountRequest {
	private Integer idAccount;
	private String accountNumber;
	private String accountType;
	private Double   initialAmount;
	private String status;
	
	private ClientRequest client;
}
