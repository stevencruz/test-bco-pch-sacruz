package com.example.test.steven.cruz.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomMovementResponse {

	private LocalDateTime date;
	private String name;
	private String accountNumber;
	private String accountType;
	private Double initialAmount;
	private String status;
	private String movementType;
	private Double value;
	private Double balance;
}