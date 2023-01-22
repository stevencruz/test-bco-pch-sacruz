package com.example.test.steven.cruz.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovementRequest {
	private Integer idMovement;
	private LocalDateTime date;
	private String movementType;
	private Double value;
	private Double balance;
	private LocalDateTime finalDate;
	private AccountRequest account;
	private Integer idClient;
}
