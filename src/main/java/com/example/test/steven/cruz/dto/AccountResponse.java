package com.example.test.steven.cruz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {
	private Integer idAccount;
	private String accountNumber;
	private String accountType;
	private Double   initialAmount;
	private String status;
	
	private ClientResponse client;
}
