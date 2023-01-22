package com.example.test.steven.cruz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClientRequest extends PersonDto{
	private Integer idClient;
	private String password;
	private Boolean status;
}
