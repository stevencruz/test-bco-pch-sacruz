package com.example.test.steven.cruz.entity;

import java.time.LocalDateTime;

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
@Table(name = "tbl_movements")
public class MovementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movement_id")
	private Integer idMovement;
	
	@Column(name = "movement_date")
	private LocalDateTime date;
	
	@Column(name = "movement_type")
	private String movementType;
	
	@Column(name = "movement_value")
	private Double value;
	
	@Column(name = "movement_balance")
	private Double balance;
	
	@ManyToOne
	@JoinColumn(name="movement_account")
	private AccountEntity account;
	
}
