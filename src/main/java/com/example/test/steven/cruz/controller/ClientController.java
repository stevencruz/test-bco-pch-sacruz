package com.example.test.steven.cruz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.steven.cruz.dto.ClientRequest;
import com.example.test.steven.cruz.dto.ClientResponse;
import com.example.test.steven.cruz.dto.FinalResponse;
import com.example.test.steven.cruz.exception.NotFoundException;
import com.example.test.steven.cruz.service.imp.ClientServiceImp;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {

	@Autowired
	private ClientServiceImp clientServiceImp;

	@GetMapping("/findAll")
	public List<ClientResponse> findAllClients() throws NotFoundException {

		var response = clientServiceImp.findAllClients();
		log.info("RESPONSE: " + response);
		return response;
	}

	@PostMapping("/save")
	public FinalResponse saveClient(@RequestBody ClientRequest clientRequest) {
		var response = clientServiceImp.saveClient(clientRequest);
		log.info("RESPONSE: " + response);
		return response;
	}

	@PutMapping("/update")
	public FinalResponse udpateClient(@RequestBody ClientRequest clientRequest) {
		var response = clientServiceImp.updateClient(clientRequest);
		log.info("RESPONSE: " + response);
		return response;
	}

	@DeleteMapping("/delete")
	public FinalResponse deleteClient(@RequestBody ClientRequest clientRequest) {
		var response = clientServiceImp.deleteClient(clientRequest);
		log.info("RESPONSE: " + response);
		return response;
	}
}