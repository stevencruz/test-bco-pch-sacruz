package com.example.test.steven.cruz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {
	private Integer idAccount;
	private String accountNumber;
	private String accountType;
	private Long   initialAmount;
	private String status;

	private ClientResponse client;
}
