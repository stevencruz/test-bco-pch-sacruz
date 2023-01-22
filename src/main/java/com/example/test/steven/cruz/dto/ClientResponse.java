package com.example.test.steven.cruz.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class ClientResponse extends PersonDto{
	private Integer idClient;
	private String password;
	private Boolean status;
	
	private List<AccountResponse> accountLists;
}
