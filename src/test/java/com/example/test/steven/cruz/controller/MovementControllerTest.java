package com.example.test.steven.cruz.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.test.steven.cruz.dto.AccountRequest;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.dto.MovementRequest;
import com.example.test.steven.cruz.service.imp.MovementServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MovementController.class)
public class MovementControllerTest {
	
	@MockBean
	private MovementServiceImp movemetServiceImp;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void shouldSaveMovementWhenReturnOk() throws JsonProcessingException, Exception {
		
		Mockito.when(movemetServiceImp.saveMovement(Mockito.any(MovementRequest.class))).thenReturn(FinalResponse.builder()
				.result(true)
				.build());	
		
		mvc.perform(post("/movement/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(MovementRequest.builder().build())))
			    .andExpect(status().isOk())
				.andExpect(jsonPath("$.result").value(true));
	}
	
	
}
