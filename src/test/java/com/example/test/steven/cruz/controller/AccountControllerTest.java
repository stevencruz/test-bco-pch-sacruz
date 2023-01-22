package com.example.test.steven.cruz.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.test.steven.cruz.dto.AccountRequest;
import com.example.test.steven.cruz.dto.AccountResponse;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.service.imp.AccountServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
	
	@MockBean
	private AccountServiceImp accountServiceImp;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
		
	
	@Test
	void shouldFindAllAccountsWhenReturnOk() throws Exception {
		var listAccountResponse = new ArrayList<AccountResponse>();
		
		listAccountResponse.add(AccountResponse.builder()
				.accountNumber("12345")
				.build());
	Mockito.when(accountServiceImp.findAllAccounts()).thenReturn(listAccountResponse);	
	
	mvc.perform(get("/account/findAll")
			.contentType(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk())
			.andExpect(jsonPath("$[0].accountNumber").value("12345"));
	}
	
	@Test
	void shouldSaveAccountWhenReturnOk() throws JsonProcessingException, Exception {
		
		Mockito.when(accountServiceImp.saveAccount(Mockito.any(AccountRequest.class))).thenReturn(FinalResponse.builder()
				.result(true)
				.build());	
		
		mvc.perform(post("/account/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(AccountRequest.builder().build())))
			    .andExpect(status().isOk())
				.andExpect(jsonPath("$.result").value(true));

	}
}
