package com.example.test.steven.cruz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public class CustomMovementResponse {

		
	private String movement_date;
	private String name;
	private String account_number;
	private String account_type;
	private String account_initial_amount;
	private String account_status;
	private String movementType;
	private String movement_value;
	private String movement_balance;
}