package com.example.test.steven.cruz.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovementResponse {
	private Integer idMovement;
	private LocalDateTime date;
	private String movementType;
	private Double value;
	private Double balance;
	
	private AccountResponse account;
}
