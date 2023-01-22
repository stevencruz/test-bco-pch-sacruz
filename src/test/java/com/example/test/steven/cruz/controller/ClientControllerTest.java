package com.example.test.steven.cruz.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.example.test.steven.cruz.dto.ClientResponse;
import com.example.test.steven.cruz.service.imp.ClientServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {
	
	@MockBean
	private ClientServiceImp clientServiceImp;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void shouldFindAllClientsWhenReturnOk() throws Exception {
		var listClientResponse = new ArrayList<ClientResponse>();
		
		listClientResponse.add(ClientResponse.builder()
				.idClient(1)
				.build());
		
		Mockito.when(clientServiceImp.findAllClients()).thenReturn(listClientResponse);	
		
		mvc.perform(get("/client/findAll")
				.contentType(MediaType.APPLICATION_JSON))
			    .andExpect(status().isOk())
				.andExpect(jsonPath("$[0].idClient").value("1")); 
	}

}
